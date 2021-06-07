import axios from 'axios';


class StudentService{

    getStudents(){
        return axios.get("http://localhost:8080/api/v1/student",
            {
                headers: {"Access-Control-Allow-Origin": "*",
                        }
            });
    }

    createStudent(student){
        return axios.post("http://localhost:8080/api/v1/student", JSON.stringify(student),
        {
            headers: {"Access-Control-Allow-Origin": "*",
                        "Content-Type": "application/json"
                    }
        } )
    }

    getStudentById(studentId){
        return axios.get("http://localhost:8080/api/v1/student/"+studentId,
        {
            headers: {"Access-Control-Allow-Origin": "*",
                    }
        } 
        )
    }

    updateStudent(student, studentId){
           return axios.put("http://localhost:8080/api/v1/student/" + studentId +
             "?firstname=" + student.firstname + 
             "&lastname=" + student.lastname +
             "&email=" + student.email +
             "&dob=" + student.dob,
                {
                headers: {"Access-Control-Allow-Origin": "*",
                "Content-Type": "text/plain"
                        }
                }
            )
    }

    deleteStudent(studentId){
        return axios.delete("http://localhost:8080/api/v1/student/" + studentId,
                            {
                            headers: {"Access-Control-Allow-Origin": "*",
                            "Content-Type": "text/plain"
                                    }
                            }
        )
    }


}
export default new StudentService()