import React, { Component } from 'react';
import './App.css';

const MinimumRequiredChoices = 2;

class NewPoll extends Component {
    constructor(props){
        super(props);

        this.state = {
            create: false,
            pollName: '',
            companyName: '',
            parameters: Array.from({ length: MinimumRequiredChoices }, () => ''),
            isgood: false,
         };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleParamChange = this.handleParamChange.bind(this);
        this.handleAddParameter = this.handleAddParameter.bind(this);
        this.handleRemoveParameter = this.handleRemoveParameter.bind(this);
    }

    async sendRequest(url, body) {
        let response = await fetch(`${process.env.REACT_APP_SERVER}/${url}`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'

            },
            body,
            credentials: 'include'
        });

        return (await response.json()).response;
    }

    async handleSubmit(event) {
        event.preventDefault();
        event.stopPropagation();

        let result = false;

        try {
            let response = await fetch(`${process.env.REACT_APP_SERVER}/createPoll`, {  //add a route for polls to be taken from
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'

                },
                body: JSON.stringify({
                    pollName: this.state.pollName,
                    companyName: this.state.companyName,
                    parameterNames: this.state.parameters,
                }),
                credentials: 'include'
            })

            result = (await response.json()).response;
        } catch (ex) {
            console.error(ex);
            console.log("fetch failed")
        }

        this.props.onResult(result);
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    handleAddParameter() {
        this.setState({ parameters: [...this.state.parameters, ''] });
    }

    handleParamChange(event) {
        let newParams = [...this.state.parameters];
        let index = parseInt(event.target.name.split('-')[1]);
        newParams[index] = event.target.value;
        this.setState({ parameters: newParams });
    }

    handleRemoveParameter(event) {
        let newParams = [...this.state.parameters];
        let index = parseInt(event.target.dataset.param);
        newParams.splice(index, 1);
        this.setState({ parameters: newParams });
    }

    render(){
        return (
            <form onSubmit={this.handleSubmit}>
                <h1>New Poll</h1>

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

                <div className="NewPoll-parameters">
                    <h3>
                        <span className="middle-align">Poll Choices:</span>

                        <button
                            className="button-round button-small middle-align"
                            disabled={this.state.parameters.length >= 10}
                            onClick={this.handleAddParameter}>
                            <span className="middle-align">+</span>
                        </button>
                    </h3>

                    {
                        this.state.parameters.map((param, index) => (
                            <div
                                className="form-input-wrapper"
                                key={index}>
                                <input
                                    data-testid={`param-${index}`} name={`param-${index}`}
                                    type="text" value={param} required
                                    placeholder={`Choice #${index + 1}`}
                                    onChange={this.handleParamChange} />

                                <button
                                    data-param={index}
                                    className="button-round button-small NewPoll-delete"
                                    onClick={this.handleRemoveParameter}
                                    disabled={this.state.parameters.length <= 2}>
                                    <span className="middle-align">x</span>
                                </button>
                            </div>
                        ))
                    }

                </div>
                <input data-testid="submit" type="submit" value="Submit"/>
            </form>
        );
    }
}

export default NewPoll;
