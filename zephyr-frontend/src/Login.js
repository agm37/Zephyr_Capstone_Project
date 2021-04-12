import { Component } from 'react';
import './App.css';

const LoginResult = {
    SUCCESS: 'success',
    FAILURE: 'failure',
    ERROR: 'error',
};

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
         };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    async handleSubmit(event) {

        event.preventDefault();
        event.stopPropagation();

        console.log("username:" + this.state.username)

        let result;
        try {
            let response = await fetch(`${process.env.REACT_APP_SERVER}/authentication`, {  //not sure this is the correct route
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',

                },
                body: JSON.stringify({
                    username: this.state.username,
                    password: this.state.password
                })
            })

            let content = await response.json()
            console.log(content);
            result = content.response ? LoginResult.SUCCESS : LoginResult.FAILURE;
        } catch (ex) {
            result = LoginResult.ERROR;
            console.error(ex);
        }



        this.props.onLoginResult(result);
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <div>
            <form onSubmit={this.handleSubmit}>
                <h1>
                    Login
                </h1>
                <div class="form-input-wrapper">
                    <label for="username">Username</label>
                    <input
                        data-testid="username" name="username" type="text"
                        value={this.state.username} required onChange={this.handleChange} />
                </div>
                <div class="form-input-wrapper">
                    <label for="password">Password</label>
                    <input
                        data-testid="password" name="password" type="password"
                        value={this.state.password} required onChange={this.handleChange} />
                </div>

                <input data-testid="submit" type="submit" value="Submit"/>
            </form>
            </div>
        );
    }
}

export default Login;
export { LoginResult };
