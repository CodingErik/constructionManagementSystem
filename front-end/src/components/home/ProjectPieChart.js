import CanvasJSReact from "../../assets/canvasjs.react";
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

function ProjectPieChart({ statusCount }) {

    const options = {

        data: [{
            type: "pie",
            dataPoints: [
                { label: "In Progress", y: statusCount.inProgress },
                { label: "Cancelled", y: statusCount.cancelled },
                { label: "Completed", y: statusCount.completed },
            ]
        }]
    }

    return (
        <div>
            <h3>Project Statuses Pie Chart</h3>
            <CanvasJSChart options={options} />
        </div>
    )
}

export default ProjectPieChart;