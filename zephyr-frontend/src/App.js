import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import VotingPage from './VotingPage';

function App() {
    return (
        <Router>
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />

                    <p>
                        Edit <code>src/App.js</code> and save to reload.
                    </p>
                    <a
                        className="App-link"
                        href="https://reactjs.org"
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        Learn React
                    </a>

                    <a
                        className="App-link"
                        href="/votingpage"
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        VotingPage
                    </a>

                    <Route
                        exact={true}
                        path="/votingpage"
                        component={VotingPage}
                    />
                </header>
            </div>
        </Router>
    );
}

export default App;
