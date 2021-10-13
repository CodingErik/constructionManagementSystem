import { useHistory } from "react-router-dom";
import decode from "jwt-decode";

function RedirectIfTokenNull() {

    let history = useHistory();
    if (!localStorage.getItem("token")) {
        history.push("/login");
    } 
    // else if, check if expired
    // if expired, delete local storage and redirect to login

}

export default RedirectIfTokenNull;
