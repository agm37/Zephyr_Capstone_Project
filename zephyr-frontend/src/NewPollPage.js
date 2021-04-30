import { Component } from 'react';
import { withRouter } from 'react-router-dom';
import NewPoll from './NewPoll';

class NewPollPage extends Component {
    constructor(props) {
        super(props);
        this.state = { failed: false };

        this.handleCreateResult = this.handleCreateResult.bind(this);
    }

    handleCreateResult(result) {
        if (result) {
            this.props.history.push('/dashboard');
        } else {
            this.setState({ failed: true });
        }
    }

    render() {
        return (
            <div>
                <NewPoll onResult={this.handleCreateResult}/>
                {this.state.failed
                    ? (<p>Failed to create poll</p>)
                    : []}
            </div>
        )
    }
}

export default withRouter(NewPollPage);
