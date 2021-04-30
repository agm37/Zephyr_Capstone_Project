import React, { Component } from 'react';
import './App.css';

class VotingPage extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            content: null,  
            pollID: 1,
            goodVote: false,
            Polls: [],
            hasPolls: false,
            selectedOption: null,
            paramNum: 0
        }

        //this.state.pollID = props.pollID ? props.pollID : 0

        this.formSubmit = this.formSubmit.bind(this)
        this.onValueChange = this.onValueChange.bind(this)
        
        this.GetParameters()
    }





          
    async GetParameters() {
        
         
       
        try {
            let response = await fetch(`${process.env.REACT_APP_SERVER}/pollInfo`, {  //add a route for polls to be taken from
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'                 

                },
                body: JSON.stringify({
                    pollID: this.state.pollID
                }),
                credentials: 'include'

            })

            console.log("fectch sent")

            this.state.Polls = await response.json()
            console.log(this.state.Polls);
            
            if(this.state.Polls.length !== 0) {
                this.setState({
                    hasPolls: true
                })
            }

        } catch (ex) {
            console.error(ex);
            console.log("fetch failed")
        }



    }






    async formSubmit(event) {
        //let response = await fetch(`${process.env.REACT_APP_SERVER}/zephyr`);
        //let { content } = await response.json();
        //this.setState({ content });

        event.preventDefault();
        event.stopPropagation();

        try {
            let response = await fetch(`${process.env.REACT_APP_SERVER}/shareholderVote`, {  //add a route for polls to be taken from
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'                 

                },
                body: JSON.stringify({
                    pollID: this.state.pollID,
                    parameterNum: this.state.paramNum,

                }),
                credentials: 'include'

            })

            console.log("vote sent")

 
            console.log(response);

            this.state.goodVote = response ? true: false

            console.log(this.state.goodVote)


        } catch (ex) {
            console.error(ex);
            console.log("vote failed")
        }

    }

    onValueChange(event) {
        this.setState({
            selectedOption: event.target.value
        });

        for( let i = 0; i < this.state.Polls.parameters.length; i++) {
            if(this.state.selectedOption === this.state.Polls.parameters[i]){
                this.state.paramNum = i
                break
            }
        }

        console.log(this.state.paramNum)

       
        
    }







    renderVotingForm = () => {
        return (
            <div>
                <form onSubmit={this.formSubmit}>
                    <div>
                        {this.renderVotingOptions()}
                    </div>
                    <p>Selected option is: {this.state.selectedOption}</p>
                    <button className="btn btn-default" type="submit">
                        Submit
                    </button>
                </form>
              
            </div>
        )

    }

    renderVotingOptions = () => {
        return (

                this.state.Polls.parameters.map((element,index) => (
                    <React.Fragment key={index}>
                        <div className="radio" >
                            <label>
                                <input
                                    type="radio"
                                    value={element}
                                    checked={this.state.selectedOption === {element}}
                                    onChange={this.onValueChange} 
                                    
                                    />
                                    {element}
                            </label>                

                        </div>
                    </React.Fragment>
                    
                ))
        )
    }


    renderDefaultView = () => {
        return(
            <p>no poll found</p>
        )
    }

    render() {
        return this.state.hasPolls ?
        this.renderVotingForm() :
        this.renderDefaultView()
     
        
    }
}

export default VotingPage;
