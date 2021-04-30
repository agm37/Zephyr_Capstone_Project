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
            selectedOption: "none"
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






    async formSubmit() {
        //let response = await fetch(`${process.env.REACT_APP_SERVER}/zephyr`);
        //let { content } = await response.json();
        //this.setState({ content });

        try {
            let response = await fetch(`${process.env.REACT_APP_SERVER}/shareholderVote`, {  //add a route for polls to be taken from
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

 
            console.log(response);

            this.state.goodVote = response ? true: false


        } catch (ex) {
            console.error(ex);
            console.log("fetch failed")
        }

    }

    onValueChange(event) {
        this.setState({
            selectedOption: event.target.value
        });
        
    }






    renderVotingForm = () => {
        return (
            <div>
                <form onSubmit={this.formSubmit}>
                    <div>
                    {this.renderVotingOptions()}
                    </div>
                </form>
                <p>Selected option is: {this.state.selectedOption}</p>
            </div>
        )

    }

    renderVotingOptions = () => {
        return (
            <div>
            {this.state.Polls.parameters.map((element,index) => (

                    <div className="radio">
                    <label>
                        <input
                            type="radio"
                            value={element}
                            checked={this.state.selectedOption === {element}}
                            onChange={this.onValueChange}
                            key={index}
                            />
                            {element}
                    </label>                

                    </div>
                
             ))}
             </div>
        )
    }

    render() {
        return this.renderVotingForm()
    }
}

export default VotingPage;
