const registratioForm = document.getElementById("registrationForm");
registratioForm.addEventListener("submit", e => {
    e.preventDefault();

    var firstName = document.getElementById("firstname");
    var lastName = document.getElementById("lastname");
    var email = document.getElementById("email");
    var password = document.getElementById("password");
    var confPass = document.getElementById("passwordConf");

    var inputs = [firstName, lastName, email, password, confPass];

    var allFilled = true;

    inputs.forEach(input => {
        if(!checkRequired(input.value)){            
            displayError(input);
            document.getElementById(input.name + "Err").innerHTML = "Kjo fushe eshte e kerkuar";
            allFilled = false
        }else{
            clearErrorMessages(input)
        }
    });
    

    //check if the fields are all filled
    if(allFilled){

        //check if passwords match, and if yes that means that the validation is passed
        if(password.value.length > 8){
            if(confPass.value == password.value) var passed = true;
            else{
                document.getElementById("passwordConfErr").innerHTML = "Fjalekalimet nuk perputhen";
                displayError(confPass)
            } 
        }else{
            document.getElementById("passwordErr").innerHTML = "Fjalekalimi duhet te jete me i gjate se 8 karaktere";
            displayError(password);
        } 
    }

    if(passed){

            //send the request to the Registration Controller
        userData = JSON.stringify( {
            "firstName": registrationForm.firstname.value,
            "lastName": registrationForm.lastname.value,
            "email" : registrationForm.email.value,
            "password": registrationForm.password.value
        })
        const formData = new FormData(registratioForm);
        axios.post("http://localhost:8080/api/v1/registration", 
                    userData,
        {
                    headers: 
                    {
                        Accept: 'application/json',
                        'Content-Type': 'application/json',
                        'Access-Control-Allow-Origin': '*', 
                    }
        }).then(response => {
                if(response.status == 200){
                    var successAlert = document.getElementById("success")
                    successAlert.classList.add("alert");
                    successAlert.classList.add("alert-success");
                    successAlert.classList.add("fs-6");
                    successAlert.classList.add("text-center");
                    successAlert.innerHTML= "U regjistruat me sukses! Aktivizoni llogarine ne emailin qe iu eshte derguar";
                    inputs.forEach(input => {
                        input.value = '';
                    });

                }
            }).catch(err => {
                document.getElementById("emailErr").innerHTML= err.response.data.message});
    }


    //  fetch("http://localhost:8080/api/v1/registration", {
    //      headers: {
    //         Accept: 'application/json',
    //         'Content-Type': 'application/json',
    //         'Access-Control-Allow-Origin': '*',
    //      },
    //      method: "post",
        //  body:JSON.stringify( {
        //     "firstName": registrationForm.firstname.value,
        //     "lastName": registrationForm.lastname.value,
        //     "email" : registrationForm.email.value,
        //     "password": registrationForm.password.value
        // })
    //  }).then(res =>{
    //         console.log(res);
    //  }).catch(err =>{
    //      console.log(err);
    //  })
 })

 //function to check if the fields are filled
 function checkRequired(data){
    if(data != '' && data != null) return true
 }

 function displayError(inputElement){
        inputElement.classList.add("border");
        inputElement.classList.add("border-danger");
        document.getElementById("password").value = "";
        document.getElementById("passwordConf").value = "";
 }
 function clearErrorMessages(inputElement){
     inputElement.classList.remove("border");
     inputElement.classList.remove("border-danger");
     document.getElementById(inputElement.name + "Err").innerHTML = " ";
 }