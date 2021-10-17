import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import HomePageSpinner from '../home/HomePageSpinner';


function MyProfileProjectsDisplay({ project }) {
    const [currentProject, setCurrentProject] = useState({});
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        setCurrentProject({ ...project });
        console.log(project);
        setIsLoading(false);
    }, [project]);

    return (
        <div>
            <h3>Projects</h3>
            <div
                className='table-responsive'
                style={{
                    maxHeight: '400px',
                    minHeight: '400px',
                    overflowY: 'scroll',

                }}
            >
                <table className='table table-hover m-auto'>
                    <thead>
                        <tr>
                            <th className='col-1'> Id</th>
                            <th className='col-3.5'>
                                Name
                            </th>
                            <th className='col-1'>
                                Status
                            </th>
                            <th className='col-2'>
                                Start Date
                            </th>
                            <th className='col-2'>
                                Deadline
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {isLoading ? (
                            <HomePageSpinner />
                        ) : (
                            <tr className='table-active' key={currentProject.id}>
                                <th scope='row'>{currentProject.id}</th>
                                <td>{currentProject.name}</td>
                                <td className={currentProject.status} style={{ fontWeight: "900" }}>{currentProject.status}</td>
                                <td>{currentProject.startDate}</td>
                                <td>{currentProject.deadline}</td>
                                <td>
                                    <Link to={{ pathname: `/SingleProjectPage/${currentProject.id}` }}>
                                        <button type='button' className='btn btn-warning'>
                                            View
                                        </button>
                                    </Link>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default MyProfileProjectsDisplay;
