import React, { Component } from 'react';
import './App.css';

class NewPoll extends Component {
    constructor(props){
        super(props);   

        this.state = {
            pollName: '',
            companyName: '',
            isgood: false
         };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        
    }

    async handleSubmit(event) {

        event.preventDefault();
        event.stopPropagation();


        try {
            let response = await fetch(`${process.env.REACT_APP_SERVER}/createPoll`, {  //add a route for polls to be taken from
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'                 

                },
                body: JSON.stringify({
                    pollName: this.state.pollName,
                    companyName: this.state.companyName
                }),
                credentials: 'include'

            })

            console.log("fectch sent")

            let goodResponse = await response.json()

            console.log(goodResponse)

            this.state.isgood = goodResponse.response ? true: false




        } catch (ex) {
            console.error(ex);
            console.log("fetch failed")
        }
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }







    render(){
        return (
            <div>
            <form onSubmit={this.handleSubmit}>
                <h1>
                    New Poll Request: 
                </h1>
                <div className="form-input-wrapper">
                    <label htmlFor="pollname">Poll Name:</label>
                    <input
                        data-testid="pollName" name="pollName" type="text"
                        value={this.state.pollName} required onChange={this.handleChange} />
                </div>
                <div className="form-input-wrapper">
                    <label htmlFor="companyName">Company Name: </label>
                    <input
                        data-testid="companyName" name="companyName" type="text"
                        value={this.state.companyName} required onChange={this.handleChange} />
                </div>


                <input data-testid="submit" type="submit" value="Submit"/>
            </form>
            </div>
        );
    }








}

export default NewPoll;
