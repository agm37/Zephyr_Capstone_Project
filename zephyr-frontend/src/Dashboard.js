import { Component, createRef } from 'react';
import { withRouter } from 'react-router-dom';
import './App.css';

const Status = {
    LOADING: 'loading',
    IDLE: 'idle',
    UPLOADING: 'uploading',
};

class Dashboard extends Component {
    constructor(props) {
        super(props);

        this.state = {
            status: Status.LOADING,
            uploadSuccess: null,
            isAdmin: false,
            shareholderInfo: [],
            pollList: [],
        };

        this.handleCreatePoll = this.handleCreatePoll.bind(this);
        this.handleOpenUpload = this.handleOpenUpload.bind(this);
        this.handleUpload = this.handleUpload.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.fileUploadRef = createRef();
    }

    async componentDidMount() {
        let isAdminPromise = fetch(`${process.env.REACT_APP_SERVER}/checkAdmin`, {
            credentials: 'include',
        });

        let shareholderInfoPromise = fetch(`${process.env.REACT_APP_SERVER}/getShareholderInfo`, {
            credentials: 'include',
        });

        let pollListPromise = fetch(`${process.env.REACT_APP_SERVER}/listPolls`, {
            credentials: 'include',
        });

        let [isAdmin, shareholderInfo, pollList] = await Promise.all(
                [isAdminPromise, shareholderInfoPromise, pollListPromise]
                    .map(promise => promise.then(reply => reply.json())));

        this.setState({
            isAdmin, shareholderInfo, pollList,
            status: Status.IDLE,
        });
    }

    handleCreatePoll() {
        this.props.history.push('/newpoll');
    }

    handleOpenUpload() {
        this.fileUploadRef.current.click();
    }

    async handleUpload(event) {
        this.setState({ uploadSuccess: null, status: Status.UPLOADING });

        try {
            let form = new FormData();
            form.append('file', event.target.files[0]);

            await fetch(`${process.env.REACT_APP_SERVER}/addShareholders`, {
                method: 'POST',
                body: form,
                credentials: 'include',
            });
        } catch (ex) {
            this.setState({ uploadSuccess: false });
            throw ex;
        } finally {
            this.setState({ status: Status.IDLE });
        }

        this.setState({ uploadSuccess: true });
    }

    async handleClose(event) {
        let closeResponse = await fetch(`${process.env.REACT_APP_SERVER}/closePoll`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'

            },
            body: JSON.stringify({
                pollID: event.target.dataset.pollid,

            }),
            credentials: 'include'
        });
        if (!(await closeResponse.json()).response) {
            console.error('close failed');
        }

        let response = await fetch(`${process.env.REACT_APP_SERVER}/listPolls`, {
            credentials: 'include',
        });
        let newPolls = await response.json();
        this.setState({ pollList: newPolls });
    }

    get shouldDisableControls() {
        return this.state.status !== Status.IDLE;
    }

    renderAdminOnlyControls() {
        if (!this.state.isAdmin) {
            return [];
        }

        return (
            <div style={{display: 'flex', flexDirection: 'row'}}>

                <button disabled={this.shouldDisableControls} onClick={this.handleCreatePoll}>
                    Create Poll
                </button>

                <button disabled={this.shouldDisableControls} onClick={this.handleOpenUpload}>
                    Import Users
                </button>

                {/* We use a hidden upload element that we auto-press with the
                    above import button */}
                <input ref={this.fileUploadRef} style={{display: 'none'}}
                       type="file" onChange={this.handleUpload}/>
            </div>
        );
    }

    renderPollSection({isActive, alreadyVoted}) {
        let polls = this.state.pollList.filter(
            p => p.active === isActive
                && (alreadyVoted !== undefined ? p.alreadyVoted === alreadyVoted : true));
        let linkPrefix;
        if ((this.state.shareholderInfo.length !== 0 || !isActive) && !alreadyVoted) {
            // Only link to pages that can either be voted on or are closed.
            linkPrefix = isActive ? 'votingpage' : 'votingresults';
        }

        return (
            <div>
                <h3>
                    {isActive ? 'Open' : 'Closed'} Polls
                    {alreadyVoted ? ' (Already Voted)' : ''}
                </h3>

                {
                    polls.length !== 0
                    ? polls.map(poll =>
                        <p key={poll.pollID}>
                            {
                                linkPrefix
                                ? (
                                    <a href={`/${linkPrefix}/${poll.pollID}`}>
                                        {poll.pollName}
                                    </a>
                                ) : poll.pollName
                            }

                            {
                                this.state.isAdmin && isActive
                                    ? (<button
                                                className="button-round button-small"
                                                onClick={this.handleClose}
                                                data-pollid={poll.pollID}>
                                            x
                                        </button>)
                                    : []
                            }
                        </p>
                    )
                    : <p>There's nothing here.</p>
                }
            </div>
        );
    }

    render() {
        if (this.state.status === Status.LOADING) {
            return (
                <p>Loading, please wait...</p>
            );
        }

        let canVote = this.state.shareholderInfo.length !== 0;

        return (
            <div>
                <h2>Welcome{
                    this.state.shareholderInfo.length ? `, ${this.state.shareholderInfo[0]}` : ''
                }!</h2>

                {this.renderAdminOnlyControls()}

                {this.state.uploadSuccess !== null
                    ? (
                        <p>Upload {this.state.uploadSuccess ? 'successful' : 'failed'}!</p>
                    ) : []}

                {this.renderPollSection({isActive: true, alreadyVoted: canVote ? false : undefined})}
                {
                    canVote
                        ? this.renderPollSection({isActive: true, alreadyVoted: true})
                        : []
                }
                {this.renderPollSection({isActive: false})}
            </div>
        );
    }
}

export default withRouter(Dashboard);
