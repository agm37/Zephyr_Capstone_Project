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
            let response = await fetch(`${process.env.REACT_APP_SERVER}/`, {  //add a route for polls to be taken from
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }

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
                            {this.state.Polls.column.map(votes => (
                                <React.Fragment key={votes.poll_id}>
                                    <dd>Catagory: {this.votes.parameter_name}</dd>
                                    <dd>Votes cast: {this.votes.vote_cout}</dd>
                                </React.Fragment>
                            ))}

                    </React.Fragment>

                ))}
                

            </div>
        )
    }

 

    renderDefaultView = () => {
        return(
            <div>
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