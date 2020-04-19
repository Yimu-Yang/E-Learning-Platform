import _ from 'lodash';
import dateFormat from 'dateformat';
import {hostUrl} from '../../config';
import React, {Component} from 'react';
import PropTypes from 'prop-types';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import {withRouter} from 'react-router-dom';
import {connect} from 'react-redux';
import {fetchDetailCourse} from '../actions/course';
import centerComponent from 'react-center-component';
import CircularProgress from 'material-ui/CircularProgress';
import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';
import defaultImage from '../../public/assets/images/default-course.jpg'
import Widget from 'react-chat-widget';
import { userInfo } from '../actions';
import ShowMore from 'react-show-more';

import Header from './header';
import Footer from './footer';
import CartBanner from './cart-banner';
import Curriculum from './curriculum';
import Comment from './comment';


import '../../styles/detail.css';

const widgetStyles = {
    avatar: {display: 'none'},
    header: {
        backgroundColor: '#334588'
    },
    launcher: {
        backgroundColor: '#334588'
    },
    message: {
        backgroundColor: '#cdd8ec'
    },
    snippet: {
        info: {
            borderLeft: '2px solid #cdd8ec'
        }
    }
}



const handleNewUserMessage = (newMessage) => {
  console.log(`New message incoming! ${newMessage}`);
  // Now send the message throught the backend API
};


const numberWithCommas = (x) => {
    let parts = parseInt(x).toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return parts.join(".");
};

@centerComponent
class DetailCourse extends Component {
    constructor(props) {
        super(props);
        this.state = {
            muiTheme: getMuiTheme(),
            dialogStyle: {display: 'none'},
            ws: new WebSocket('ws://localhost:3030'),
            messages: []
        };
    }

    

    static childContextTypes = {
        muiTheme: PropTypes.object
    };

    getChildContext() {
        return {muiTheme: this.state.muiTheme};
    }

    static propTypes = {
        topOffset: PropTypes.number,
        leftOffset: PropTypes.number
    };

    componentDidMount() {
        this.setState({
            dialogStyle: {
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                marginTop: 100,
                marginBottom: 100,
                width: '100%',
                height: '100%',
                top: this.props.topOffset,
                left: this.props.leftOffset
            }
        });

        window.scrollTo(0, 0);

        const token = localStorage.getItem('token');
        if(token) {
            console.log('---')
            this.props.userInfo();
        }

        localStorage.setItem('course', this.props.match.params.id);
        this.props.fetchDetailCourse(this.props.match.params.id);

        this.state.ws.onopen = () => {
            // on connecting, do nothing but log it to the console
            console.log('socket connected')
        }

        this.state.ws.onmessage = evt => {
            // on receiving a message, add it to the list of messages
            const message = JSON.parse(evt.data)
            console.log('on receive msg: ', message);
            this.addMessage(message)
        }

        this.state.ws.onclose = () => {
            console.log('disconnected')
            // automatically try to reconnect on connection loss
            this.setState({
                ws: new WebSocket(URL),
            })
        }
    }

    addMessage = message => this.setState(state => ({ messages: [
        message, ...state.messages] }))

    sendMessage = (newMessage) => {
        console.log(`New message incomig! ${newMessage}`, this.props);
        const massge = {
            type: 'text',
            text: `<${this.props.user.email}> : ${newMessage}`
        }
        // Now send the message throught the backend API
        this.state.ws.send(JSON.stringify(massge))
    }

    renderState = () => {
        // if (this.props.hasError) {
        //     return (
        //         <div className="alert alert-danger">
        //             <div style={{textAlign: 'center'}}>
        //                 <strong>There was a loading error</strong>
        //             </div>
        //         </div>
        //     );
        // }

        if (this.props.isLoading) {
            return (
                <div style={this.state.dialogStyle}>
                    <CircularProgress size={60} thickness={7}/>
                </div>);
        }
    };

    authorNames = (authors) => {
        return _.map(authors, (author, i) => {
            let decorator = ', ';
            if(i === authors.length-1) {
                decorator = '';
            }

            return (
                <span key={i}>
                    <a className="text-emphasis-first">{author.name}</a><a className="text-white">{decorator}</a>
                </span>
            );
        });
    };

    renderAuthor = (authors) => {
        return _.map(authors, (author, i) => {
            let decorator = '<br/>';
            if(i === authors.length-1) {
                decorator = '';
            }

            return (
                <div key={i}>
                    <div className="row">
                        <div className="col-sm-1"/>
                        <div className="col-sm-4">
                            <div><img src={`${hostUrl}/images/${author.avatar}`}/></div>
                            <br/>
                            <div><span className="text-size-third text-bold">{author.average}</span> <span className="text-size-fourth">Average rating</span></div>
                            <div><i className="fa fa-home text-emphasis-second"/><span className="text-size-third text-bold">{numberWithCommas(author.reviews)}</span> <span className="text-size-fourth">Reviews</span></div>
                            <div><span className="text-size-third text-bold">{numberWithCommas(author.students)}</span> <span className="text-size-fourth">Students</span></div>
                            <div><span className="text-size-third text-bold">{numberWithCommas(author.courses)}</span> <span className="text-size-fourth">Courses</span></div>
                            <br/>
                        </div>
                        <div className="col-sm-6">
                            <div className="text-size-fifth text-emphasis-fourth text-bold">{author.name}</div>
                            <div className="text-size-sixth">
                                <ShowMore
                                    lines={5}
                                    more='Show more'
                                    less='Show less'
                                    anchorClass=''
                                >
                                    <div dangerouslySetInnerHTML={ {__html: unescape(author.description)} }/>
                                </ShowMore>
                            </div>
                        </div>
                        <div className="col-sm-1"/>
                    </div>
                    <div className="row">
                        <div className="text-white">{decorator}</div>
                    </div>
                </div>
            );
        });
    };

    renderCourse = () => {
        const {course} = this.props;

        if (!course) return (<div>&nbsp;</div>);

        return (
            <div className="container-fluid">
                <div className="row header-top">
                    <div className="col-sm-12">
                        <div className="container">
                            <div className="row body-content">
                                <div className="col-sm-8">
                                    <br/>
                                    <CardMedia
                                        overlay={<CardTitle title="" subtitle={course.course_name} />}
                                    >
                                        <img src={_.startsWith(course.image_url, 'http') ? course.image_url : defaultImage} alt="" />
                                    </CardMedia>
                                    <br/>
                                    <div className="text-white text-size-first">{course.course_name}</div>
                                    <br/>
                                    <div className="text-white text-size-second">{course.subtitle}</div>
                                    <div className="text-white text-size-third">rating: <span
                                        className="text-emphasis-first">{course.rating}</span> 
                                        {/* (<span className="text-emphasis-second">{numberWithCommas(course.reviews)}</span> reviews) */}
                                    </div>
                                    {/* <div className="text-white text-size-third text"><span
                                        className="text-emphasis-third">{numberWithCommas(course.enrolled)}</span> students enrolled
                                    </div> */}
                                    <div className="text-white text-size-third text">Created
                                        by {course.provider}</div>
                                    <div className="text-white text-size-third text">Last updated <span
                                        className="text-emphasis-third">{dateFormat(course.updated, "m/yyyy")}</span>
                                    </div>
                                </div>
                                <div className="col-sm-4">
                                    <div style={{marginTop:30}} className="hidden-xs">
                                    </div>
                                    <br/>
                                    <CartBanner course={course}/>
                                </div>
                            </div>
                            <div className="row body-content hidden-xs">
                                <div className="col-sm-12">
                                    <br/>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                </div>
                <div className="container">
                    <div className="row body-content">
                        <div className="col-sm-offset-1 col-sm-10">

                            <br/>
                            <br/>
                            <div className="text-size-second text-bold">Description</div>
                            <br/>
                            <ShowMore
                                lines={5}
                                more='Show more'
                                less='Show less'
                                anchorClass=''
                            >
                                <div dangerouslySetInnerHTML={{ __html: unescape(course.course_description)} }/>
                            </ShowMore>
                            <br/>
                            <br/>
                            {/* <Curriculum/>
                            <br />
                            <ul>
                                <li><a href={course.video_url}>Video URL</a></li>
                                <li><a href={course.coursetalk_url}>Course Talk URL</a></li>
                                <li><a href={course.course_redirect_url}>Course Redirect URL</a></li>
                                <li><a href={course.course_actual_url}>Course Actual URL</a></li>
                            </ul> */}
                        </div>
                    </div>
                </div>
                {/* <div className="container">
                    <div className="row body-content">
                        <div className="col-sm-offset-1 col-sm-10">
                            <br/>
                            <br/>
                            <div className="text-size-second text-bold text-center">About the Instructor</div>
                        </div>
                    </div>
                    <br/>
                </div>
                <div className="container">
                    {this.renderAuthor(course._authors)}
                    <br/>
                </div> */}
                <br/>
                <div className="container">
                    <Comment/>
                </div>
            </div>
        );
    };

    render() {
        return (
            <div>
                <Header {...this.props}/>
                <div>
                    {this.renderState()}
                </div>
                <div style={{
                    marginTop: 20,
                    marginBottom: 20
                }}>
                    {this.renderCourse()}
                </div>
                <Footer {...this.props}/>
                <Widget
                  responseMessages={this.state.messages}
                  handleNewUserMessage={this.sendMessage}
                  stylesInjected={widgetStyles}
                  profileAvatar={null}
                  title="iStudy"
                  subtitle="Let's chat"
                />
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        course: state.fetchDetailCourseDone,
        hasError: state.fetchCourseFailure,
        user: state.auth.user,
        isLoading: state.fetchCourseLoading
    };
}

const mapDispatchToProps = dispatch => {
    return {
        fetchDetailCourse: (course_no) => dispatch(fetchDetailCourse(course_no)),
        userInfo: () => dispatch(userInfo())
    }
};

export default DetailCourse = withRouter(connect(mapStateToProps, mapDispatchToProps)(DetailCourse));
