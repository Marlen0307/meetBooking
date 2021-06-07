import React, { Component } from 'react';

class Header extends Component {
    constructor(props){
        super(props)
        this.state = {

        }
    }

    render() {
        return (
            <div>
                <nav className="navbar navbar-dark bg-dark">
                    <div className="container-fluid justify-content-start">
                        <a href = "/" className="navbar-brand mb-0 h1">Menaxhim i studenteve</a>
                        <a className = "nav-link text-white text-decoration-none" href = "/">Lista e Studenteve</a>

                    <div className = "m-auto d-flex flex-grow-1 justify-content-end">
                        <a href = "/logout" className = "btn btn-primary text-white">Log Out</a>
                    </div>
                    </div>
                    
                </nav>
            </div>
        );
    }
}

export default Header;