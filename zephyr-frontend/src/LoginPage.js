import { Component } from 'react';
import { withRouter } from 'react-router-dom';
import Login, { LoginResult } from './Login';

class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = { result: null };

        this.handleLoginResult = this.handleLoginResult.bind(this);
    }

    async handleLoginResult(result) {
        if (result === LoginResult.SUCCESS) {
            this.props.history.push('/dashboard');
        } else {
            this.setState({ result });
        }
    }

    render() {
        return (
            <div>
                <Login onLoginResult={this.handleLoginResult}/>
                {this.state.result ? (
                    <p>Login {this.state.result}</p>
                ) : []}
            </div>
        );
    }
}

export default withRouter(LoginPage);
