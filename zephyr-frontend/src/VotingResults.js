import React, { Component } from 'react';
import './App.css';

class VotingResults extends Component {
    constructor(props){
        super(props)

        this.state = {
            Polls: [],
            hasPolls: false
        }

        
       
        try {
            let response = await fetch(`${process.env.REACT_APP_SERVER}/pollInfo`, {  //add a route for polls to be taken from
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    poll_id: 1
                })

            })

            this.state.Polls = await response.json()
            console.log(this.state.Polls);
            
            if(this.state.Polls.length != 0) {
                this.setState({
                    hasPolls: true
                })
            }

        } catch (ex) {
            console.error(ex);
        }



    }


    renderPollResuts = () => {
        return (
            <div>
                <p>Hi this is the voting results page</p>
                <table>
                    {this.PollTable}
                </table>

            </div>
        )

    }

    PollTable = () => {
        return (
            <div>
                {this.state.Polls.map(element => (
                    <React.Fragment key={element.column.poll_id}>
                        <dd>ID: {element.column.poll_id}</dd>
                        <dd>Votes sent: {element.column.num_votes}</dd>
                        <dd>Votes remaining: {element.column.num_votes_remaining}</dd>

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