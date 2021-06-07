import React, { Component } from 'react';
import StudentService from '../services/StudentService';

class ListStudentsComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            students: []
        }

        this.addStudent = this.addStudent.bind(this);
        this.editStudent = this.editStudent.bind(this);
        this.deleteStudent = this.deleteStudent.bind(this);
    }
    componentDidMount(){
        StudentService.getStudents().then( res => {
            this.setState({
                students : res.data
            })
        })
    }

    addStudent(){
        this.props.history.push('/addStudent/-1');
    }

    editStudent(studentId){
        this.props.history.push(`/addStudent/${studentId}`);
    }

    deleteStudent(studentId){
        StudentService.deleteStudent(studentId).then((res) =>{
            if(res.status === 200){
                window.location.reload();
            }
        });
    }
    render() {
        return (
            <div>
                <h2 className = "text-center mt-4">Lista e Studenteve</h2>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                        <th scope = "row" >#</th>
                            <th className = "text-center">Emri</th>
                            <th className = "text-center">Mbiemri</th>
                            <th className = "text-center">Mosha</th>
                            <th className = "text-center">Email</th>
                            <th className = "text-center">
                                <button className = "btn btn-primary" onClick = {this.addStudent}>Shto Student</button>
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            this.state.students.map(student =>
                                <tr key = {student.id}>
                                    <th scope = "row" >{student.id}</th>
                                    <td className = "text-center" >{student.firstname}</td>
                                    <td className = "text-center">{student.lastname}</td>
                                    <td className = "text-center">{student.age}</td>
                                    <td className = "text-center">{student.email}</td>
                                    <td className = "d-flex justify-content-center">
                                        <button onClick = {() => this.editStudent(student.id)} className = "btn btn-success mx-2">Ndrysho</button>
                                        <button  onClick = {() => this.deleteStudent(student.id)} className = "btn btn-danger mx-2">Fshi</button>
                                    </td>
                                </tr>
                            )
                        }
                    </tbody>


                </table>
            </div>
        );
    }
}

export default ListStudentsComponent;