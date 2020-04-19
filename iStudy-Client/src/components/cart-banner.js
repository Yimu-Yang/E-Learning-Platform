import React, {Component} from 'react';
import PropTypes from 'prop-types';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import Dialog from 'material-ui/Dialog';
import Paper from 'material-ui/Paper';
import RaisedButton from 'material-ui/RaisedButton';
import {withRouter} from 'react-router-dom';
import {connect} from 'react-redux';
import {buyCourse, addCart} from '../actions/cart';
import {userInfo} from '../actions';

import {hostUrl} from '../../config';

import SignIn from '../auth/signin';

import '../../styles/detail.css';

const numberWithCommas = (x) => {
    if (_.startsWith(x, 'FREE')) {
        return x;
    }
    let parts = parseInt(x).toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return parts.join(".");
};

const styles = {
    dialogRoot: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        paddingTop: 0
    },
    dialogContent: {
        position: "relative",
        width: "80vw",
        transform: "",
    },
    dialogBody: {
        paddingBottom: 0
    }
};

class CartBanner extends Component {
    constructor(props) {
        super(props);
        this.state = {
            muiTheme: getMuiTheme(),
            open: false
        };
    }

    static childContextTypes = {
        muiTheme: PropTypes.object
    };

    getChildContext() {
        return {muiTheme: this.state.muiTheme};
    }

    componentDidMount() {
        const token = localStorage.getItem('token');
        if(token) {
            this.props.userInfo();
        }
    }

    handleOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {
        this.setState({open: false});
    };

    learn = () => {
        if (this.props.logged) {
            const {course} = this.props;

            this.props.buyCourse(course.no);
        }
        else {
            this.handleOpen();
        }
    };

    buyCourse = () => {
        if (this.props.logged) {
            const {course} = this.props;

            this.props.buyCourse(course.id);
        }
        else {
            this.handleOpen();
        }
    };

    addToCart = (unenroll=false) => {
        if (this.props.logged) {
            const { course, user} = this.props;
            console.log('cart user: ', user, course);
            this.props.addCart(course.id, user.email, unenroll);
        }
        else {
            this.handleOpen();
        }
    };

    renderDialog = () => {
        return (
            <div>
                <Dialog
                    contentStyle={ styles.dialogContent }
                    bodyStyle={ styles.dialogBody }
                    style={ styles.dialogRoot }
                    modal={false}
                    open={this.state.open}
                    onRequestClose={this.handleClose}
                    repositionOnUpdate={false}
                    autoScrollBodyContent={true}
                >
                    <SignIn redirect={this.handleClose} dialog={true}/>
                    <br/>
                    <br/>
                </Dialog>
            </div>
        );
    };

    renderButton = (course) => {
        const {user} = this.props;

        let filtered = null;

        if (course) {
            if (user && user.courses) {
                filtered = user.courses.filter(function (_course) {
                    if (_course.no === course.no) {
                        return _course;
                    }
                });
            }
        }

        if(filtered) {
            if (filtered.length > 0) {
                if (filtered[0].learn) {
                    return (
                        <div>
                            <div style={{textAlign: 'center', marginBottom: 6}}>
                                <RaisedButton label="Learn the Course" labelStyle={{textTransform: 'none'}}
                                              primary={true}
                                              fullWidth={true} onTouchTap={() => this.learn()}/>
                            </div>
                        </div>
                    );
                }
            }
        }


        const enrolled = JSON.parse(localStorage.getItem("enrolled"));
        const course_id = parseInt(localStorage.getItem("course"));

        const isEnrolled = enrolled.includes(course_id);
        // console.log('enrolled: ', enrolled, course_id, isEnrolled);
        
        const enrollButton = (<RaisedButton label={isEnrolled ? 'UnEnroll' : 'Enroll'} size={40} primary={true} fullWidth={true} onTouchTap={() => this.addToCart(isEnrolled)} />);

        return (
            <div>
                <div style={{textAlign: 'center', marginBottom: 6}}>
                    <RaisedButton label="Go to course" secondary={true} fullWidth={true}
                        href={course.course_actual_url}
                        target="_blank"
                    />
                </div>
                <div style={{textAlign: 'center', marginBottom: 6}}>
                    {enrollButton}
                </div>
            </div>
        );
    };

    renderPicture = (course) => {
        if(course.picture) {
            return (
                <div>
                    <img width="100%" height="100%" src={`${hostUrl}/images/${course.picture}`}/>
                </div>
            );
        }
    };

    renderBanner = () => {
        const {course} = this.props;

        return (
            <div>
                <Paper zDepth={2} style={{
                    width: '100%',
                    height: '100%',
                    overflow: 'hidden',
                    backgroundColor: '#FFF',
                    display: 'inline-block'
                }}>
                    <div style={{
                        marginTop:6,
                        marginLeft:6,
                        marginRight:6
                    }}>
                    <div style={{marginBottom:6}}/>
                    <div style={{textAlign: 'center'}}>
                        {this.renderPicture(course)}
                    </div>
                    <div style={{marginTop:20}}/>
                    {this.renderButton(course)}
                    </div>
                </Paper>
            </div>
        );
    };

    render() {
        return (
            <div>
                {this.renderDialog()}
                {this.renderBanner()}
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        logged: state.auth.logged,
        user: state.auth.user
    };
}

const mapDispatchToProps = dispatch => {
    return {
        buyCourse: (course_no) => dispatch(buyCourse(course_no)),
        addCart: (course_no, user_name, unenroll) => dispatch(addCart(course_no, user_name, unenroll)),
        userInfo: () => dispatch(userInfo())
    }
};

export default CartBanner = withRouter(connect(mapStateToProps, mapDispatchToProps)(CartBanner));
