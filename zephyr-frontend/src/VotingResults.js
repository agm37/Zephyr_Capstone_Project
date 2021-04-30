import React, { Component } from 'react';
import { Pie } from 'react-chartjs-2';
import './App.css';

class VotingResults extends Component {
    constructor(props){
        super(props)

        this.state = {
            Polls: [],
            hasPolls: false,
            pollID: parseInt(props?.match?.params?.pollID || '0'),
        }

        console.log(this.state.pollID)
    }

    async componentDidMount() {
        await this.GetVotes();
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

            if(this.state.Polls.parameters.length !== 0) {
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
        let totalVotes = this.state.Polls.voteCount.reduce((a, b) => a + b, 0);
        if (totalVotes === 0) {
            return (
                <p>No votes were cast.</p>
            );
        }

        let filledParameters = this.state.Polls.parameters.filter(p => p).length;

        // Colors from:
        // https://tableaufriction.blogspot.com/2012/11/finally-you-can-use-tableau-data-colors.html
        let data = {
            labels: this.state.Polls.parameters.slice(0, filledParameters),
            color: 'white',
            datasets: [
                {
                    data: this.state.Polls.voteCount.slice(0, filledParameters),
                    backgroundColor: [
                        'rgb(255, 127, 14)',
                        'rgb(214, 39, 40)',
                        'rgb(148, 103, 189)',
                        'rgb(44, 160, 44)',
                        'rgb(31, 119, 180)',
                        'rgb(227, 119, 194)',
                        'rgb(188, 189, 134)',
                        'rgb(140, 86, 75)',
                        'rgb(127, 127, 127)',
                        'rgb(23, 190, 207)',
                    ],
                }
            ],
        };

        let options = {
            color: 'white',
            plugins: {
                legend: {
                    labels: {
                        font: {
                            family: 'Nunito, sans-serif',
                            size: 14,
                        },
                    },
                },
            },
        };

        return (
            <div>
                <h3>Voting Results:</h3>
                <Pie data={data} options={options}/>
            </div>
        )

    }

    PollTable = () => {
        return (
            <div>
                {this.state.Polls.parameters.map((element,index) => (
                    <React.Fragment key={index}>
                        <dd className="Parameters">Parameters: {element}   |VoteCount: {this.state.Polls.voteCount[index]}</dd>
                    </React.Fragment>

                ))}


            </div>
        )
    }



    renderDefaultView = () => {
        return(
            <div>
                <p>Loading poll results...</p>
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
