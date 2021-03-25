import { Component } from 'react';
import './App.css';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            username: '',
            password: ''
         };


        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }



    async handleSubmit(event) {

        event.preventDefault();
        event.stopPropagation();

        console.log("username:" + this.state.username)

        const request = new Request(`${process.env.REACT_APP_SERVER}/authentication`, {  //not sure this is the correct route
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: this.state.username,
                password: this.state.password
            })
        }) 

        let response = await fetch(request)
        let content = response.json()
        console.log(content)



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
                <label>
                    Username :
                </label>
                <br/>
                <input name="username" type="text" value={this.state.username} required onChange={this.handleChange} />
                <br/>
                <label>
                    Password:
                </label>
                <br/>
                <input name="password" type="text" value={this.state.password} required onChange={this.handleChange} />
                <br/>


                <input type="submit" value="Submit"/>
            </form>
            </div>
    
        );
    }

 



}

export default Login;