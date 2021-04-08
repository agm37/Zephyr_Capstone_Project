import { Component } from 'react';
import './App.css';

class VotingPage extends Component {
    constructor(props) {
        super(props);
        this.state = { content: null };

        this.handleClick = this.handleClick.bind(this);
    }

    async handleClick() {
        let response = await fetch(`${process.env.REACT_APP_SERVER}/zephyr`);
        let { content } = await response.json();
        this.setState({ content });
    }

    render() {
        return (
            <div>
                <p>Welcome to the voting page!</p>
                <button onClick={this.handleClick}>Query server</button>
                <p>{this.state.content}</p>
            </div>
        );
    }
}

export default VotingPage;
