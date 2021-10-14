import React, { useEffect, useState } from 'react';
import './Materials.css';

export default function Materials(props){

    const [materials, setMaterials] = useState();

    useEffect(() => {
        setMaterials(props.materials)
    },[props])


    return(
        <div className="materialsWrapper row">
            {materials && Object.entries(materials).map(key => {
                return (
                    <div className="col-6">
                        {
                            (key[0] !== "id" && key[0] !== "projectId")
                            &&
                            <div className="materialOrMachineryIcon">
                                <p className="textElementMaterials">{(key[0].charAt(0)).toUpperCase() + key[0].substring(1, key[0].length)}</p>
                                <p className="textElementMaterials">{key[1]}/1000 lbs</p>
                            </div>
                        }
                    </div>
                )
            })}
        </div>
    )
}