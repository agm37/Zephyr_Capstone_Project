import React, { Component } from 'react';
import './App.css';

class VotingResults extends Component {
    constructor(props){
        super(props)

        this.state = {
            Polls: [],
            hasPolls: false,
            pollID: 0
        }

        this.state.pollID = props.pollID ? props.pollID : 0

        console.log(this.state.pollID)

        this.GetVotes()
    }



        
    async GetVotes() {
        
       
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


    renderPollResuts = () => {
        return (
            <div>
                <p>Hi this is the voting results page</p>
                <p>Voting Results: </p>
                <table>
                    {this.PollTable()}
                </table>

            </div>
        )

    }

    PollTable = () => {
        return (
            <div>
                {this.state.Polls.parameters.map((element,index) => (
                    <React.Fragment key={index}>
                        <dd className="Parameters">Parameters: {element}   VoteCount: {this.state.Polls.voteCount[index]}</dd>
                    </React.Fragment>

                ))}
                

            </div>
        )
    }

 

    renderDefaultView = () => {
        return(
            <div>
                <p>Hi this is the voting results page</p>
                <p>No search results found</p>
            </div>

        )
    }


    render(){
        return this.state.hasPolls ?
        this.renderPollResuts() :
        this.renderDefaultView()
    
    }


}

export default VotingResults;