import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import ProjectAPI from "../api/index";
function SingleProjectPage({ projectid }) {
  const { projectId } = useParams();
  useEffect(() => {

  },[]);
  return <div></div>;
}

export default SingleProjectPage;
