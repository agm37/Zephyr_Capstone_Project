import { Component } from 'react';
import { withRouter } from 'react-router-dom';
import Login from './Login';

class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = { result: null };

        this.handleLoginResult = this.handleLoginResult.bind(this);
    }

    async handleLoginResult(result) {
        this.setState({ result });
    }

    render() {
        return (
            <div>
                <Login onLoginResult={this.handleLoginResult}/>
                {this.state.result ? (
                    <p>Login result: {this.state.result}</p>
                ) : []}
            </div>
        );
    }
}

export default withRouter(LoginPage);
