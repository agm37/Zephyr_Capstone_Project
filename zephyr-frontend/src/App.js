import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom';
import VotingPage from './VotingPage';
import LoginPage from './LoginPage'
import VotingResults from './VotingResults'
import NewPollPage from './NewPollPage'

function App() {
    return (
        <Router>
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />

                    <Route
                        exact
                        path="/newpoll"
                        component={NewPollPage}

                    />

                    <Route
                        exact
                        path="/votingresults" >
                            <VotingResults pollID={1}/>
                        
                    </Route>

                    {/* <Route
                        exact
                        path="/"
                        render={() => (<Redirect to="/login" />)}
                    /> */}

                    <Route
                        exact
                        path="/votingpage"
                        component={VotingPage}
                    />

                    <Route
                        exact
                        path="/login"
                        component={LoginPage}
                    />

                </header>
            </div>
        </Router>
    );
}

export default App;
