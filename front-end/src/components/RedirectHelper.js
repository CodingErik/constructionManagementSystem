import { useHistory } from "react-router-dom";

function RedirectIfTokenNull() {

    let history = useHistory();
    if (!localStorage.getItem("token")) {
        history.push("/login");
    }

}

export default RedirectIfTokenNull;
