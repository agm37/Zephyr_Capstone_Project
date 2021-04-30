import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import './App.css';

class VotingPage extends Component {
    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            content: null,
            goodVote: false,
            Polls: { parameters: [] },
            hasPolls: false,
            selectedOption: null,
            paramNum: 0,
            pollID: parseInt(props?.match?.params?.pollID || '0'),
        }

        this.formSubmit = this.formSubmit.bind(this)
        this.onValueChange = this.onValueChange.bind(this)
    }

    async componentDidMount() {
        await this.GetParameters();
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
            let paramNum;
            for( let i = 0; i < this.state.Polls.parameters.length; i++) {
                if(this.state.selectedOption === this.state.Polls.parameters[i]){
                    // + 1 because parameters are 1-indexed.
                    paramNum = i + 1
                    break
                }
            }


            let response = await fetch(`${process.env.REACT_APP_SERVER}/shareholderVote`, {  //add a route for polls to be taken from
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'

                },
                body: JSON.stringify({
                    pollID: this.state.pollID,
                    parameterNum: paramNum,

                }),
                credentials: 'include'

            })

            console.log("vote sent")
            console.log("Param num is:" + paramNum)
            console.log("Vote is for:" + this.state.selectedOption)


            console.log(response);

            if ((await response.json()).response) {
                this.props.history.push('/dashboard');
            }



        } catch (ex) {
            console.error(ex);
            console.log("vote failed")
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
                <form onSubmit={this.formSubmit} className="VotingPage-form">
                    {
                        this.state.selectedOption !== null
                        ? <p>Selected option is: {this.state.selectedOption}</p>
                        : <p>Select an option to continue</p>
                    }
                    <div className="VotingPage-choices">
                        {this.renderVotingOptions()}
                    </div>
                    <button disabled={this.state.selectedOption === null}
                            className="btn btn-default" type="submit">
                        Submit
                    </button>
                </form>

            </div>
        )

    }

    renderVotingOptions = () => {
        return (

                this.state.Polls.parameters
                    .filter(element => element.length !== 0)
                    .map((element,index) => (
                        <React.Fragment key={index}>
                            <div className="radio" >
                                <label>
                                    <input
                                        type="radio"
                                        value={element}
                                        checked={this.state.selectedOption === element}
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
            <p>Loading poll choices...</p>
        )
    }

    render() {
        return this.state.hasPolls ?
        this.renderVotingForm() :
        this.renderDefaultView()


    }
}

export default withRouter(VotingPage);
