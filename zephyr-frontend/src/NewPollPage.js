import { Component } from 'react';
import { withRouter } from 'react-router-dom';
import NewPoll from './NewPoll';

class NewPollPage extends Component {
    constructor(props) {
        super(props);
        this.state = { hasFailedAttempt: false };

        this.handleCreateResult = this.handleCreateResult.bind(this);
    }

    handleCreateResult(result) {
        this.setState({ result });
    }

    render() {
        return (
            <div>
                <NewPoll onResult={this.handleCreateResult}/>
                {this.state.result !== null
                    ? [
                        this.state.result
                            ? <p>Successfully created poll!</p>
                            : <p>Failed to create poll</p>
                    ] : []}
            </div>
        )
    }
}

export default withRouter(NewPollPage);
