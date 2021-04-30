import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom';
import Dashboard from './Dashboard';
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
                        path="/dashboard"
                        component={Dashboard} />

                    <Route
                        exact
                        path="/votingresults/:pollID"
                        component={VotingResults} />

                    {/* <Route
                        exact
                        path="/"
                        render={() => (<Redirect to="/login" />)}
                    /> */}

                    <Route
                        exact
                        path="/votingpage/:pollID"
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
