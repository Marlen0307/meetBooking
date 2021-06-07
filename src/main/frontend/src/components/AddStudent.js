import React, { Component } from 'react';
import StudentService from '../services/StudentService';

class AddStudent extends Component {
    constructor(props){
        super(props);
        this.state = {
            studentId: this.props.match.params.studentId,
            firstname : '',
            lastname : '',
            dob : '',
            email : '',
            emailTaken: false
        }

        this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
        this.handleLastNameChange = this.handleLastNameChange.bind(this);
        this.handleBirthdayChange = this.handleBirthdayChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    componentDidMount(){
        if(this.state.studentId == -1){
            return
        }
        StudentService.getStudentById(this.state.studentId).then((res)=>{
            let student = res.data;
            this.setState({
                firstname: student.firstname,
                lastname: student.lastname,
                email : student.email,
                dob: student.dob
            });
        }); 
    }

    handleFirstNameChange = (e)=>{
        this.setState({
            firstname: e.target.value
        })
    }

    handleLastNameChange = (e) => {
        this.setState({
            lastname: e.target.value
        })
    }

    handleBirthdayChange = (e) => {
        this.setState({
            dob: e.target.value
        })
    }

    handleEmailChange = (e) => {
        this.setState({
            email: e.target.value
        })
    }

    handleSubmit = (e)=>{
        e.preventDefault();
        let student = {
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            dob: this.state.dob,
            email: this.state.email
        }
        
        if(this.state.studentId == -1){
            StudentService.createStudent(student).then(res=>{
                if(res.status === 200){
                    this.props.history.push("/");
                }
            }).catch(e =>{
                this.setState({emailTaken: true});
            })
        }else{
            StudentService.updateStudent(student, this.state.studentId).then((res)=>{
                this.props.history.push("/");
            }).catch(e =>{
                this.setState({emailTaken: true});
            });
        }

    }

    render() {
        return (
            <div className = " col-4 p-3 text-center m-auto">
                <h3 className = "mb-4">{this.state.studentId == -1 ? 'Shto Student': 'Ndrysho Studentin'}</h3>
                <form onSubmit = {this.handleSubmit}>
                <div className="mb-4">
                    <label htmlFor="firstname" className="visually-hidden">Emri</label>
                    <input required className="form-control bg-light p-3" placeholder="Emri" 
                     value = {this.state.firstname} onChange = {this.handleFirstNameChange}   type="text" name="firstname" id="firstname"/>
                </div>

                <div className="mb-4">
                    <label htmlFor="lastname" className="visually-hidden">Mbiemri</label>
                    <input required type="text" className="form-control bg-light p-3"
                    value = {this.state.lastname} onChange = {this.handleLastNameChange} placeholder="Mbiemri" name="lastname" id="lastname"/>
                </div>
            
                <div className="mb-4">
                    <label htmlFor="email" className="visually-hidden">Email:</label>
                    <input type="email" required className="form-control bg-light p-3"
                    value = {this.state.email} onChange = {this.handleEmailChange} placeholder="Email" name="email" id="email"/>
                    <div id="emailErr" className="mt-1 text-start text-danger">{this.state.emailTaken ? 'This email is taken':''}</div>
                </div>

                <div className="mb-4">
                    <label htmlFor="password" className="visually-hidden">Birthday</label>
                    <input required type="date" name = "birthday" 
                    value = {this.state.dob} onChange = {this.handleBirthdayChange} className="form-control bg-light p-3"/>
                </div>

                <div>
                    <button  className="btn-primary rounded p-2 fs-5 w-100" type="submit">{this.state.studentId == -1 ? 'Shto Student' : 'Ndrysho'}</button>
                </div> 

                </form>
            </div>
        );
    }
}

export default AddStudent;