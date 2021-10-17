import React, { useEffect, useState } from 'react';
import './Resources.css';
import AddMaterialModal from './AddMaterialModal';
import AddMachineModal from './AddMachineModal';
import ConfirmReturnMachinesModal from './ConfirmReturnMachinesModal';
import { MaterialAPI, MachineryAPI } from '../../api';
import brickIcon from '../../assets/brick.png';
import cementIcon from '../../assets/cement.png';
import lumberIcon from '../../assets/lumber.png';
import steelIcon from '../../assets/steel.png';
import craneIcon from '../../assets/crane.png';
import drillIcon from '../../assets/drill.png';
import forkliftIcon from '../../assets/forklift.png';
import ladderIcon from '../../assets/ladder.png';

export default function ResourcesForSingleProjectPage({
  projectId,
  hasAuthority,
}) {
  const [maxMaterialAmount, setMaxMaterialAmount] = useState({
    brick: 1000,
    cement: 1000,
    lumber: 1000,
    steel: 1000,
  });
  const [maxMachineAmount, setMaxMachineAmount] = useState({
    crane: 30,
    drill: 30,
    forklift: 30,
    ladder: 30,
  });
  const [materials, setMaterials] = useState();
  const [machines, setMachines] = useState();
  const [materialsId, setMaterialsId] = useState(0);
  const [machineId, setMachinesId] = useState(0);

  useEffect(() => {
    async function fetchData() {
      const materialInfo = await MaterialAPI.getMaterialsByProjectId(projectId);
      const machineInfo = await MachineryAPI.getMachineryByProjectId(projectId);
      const materialWarehouseInfo =
        await MaterialAPI.getWarehouseMaterialsInventory();
      const machineWarehouseInfo =
        await MachineryAPI.getWarehouseMachineryInventory();

      const materialHolder = {
        brick: {
          name: 'Brick',
          amount: materialInfo.data.brick ? materialInfo.data.brick : 0,
          icon: brickIcon,
        },
        cement: {
          name: 'Cement',
          amount: materialInfo.data.cement ? materialInfo.data.cement : 0,
          icon: cementIcon,
        },
        lumber: {
          name: 'Lumber',
          amount: materialInfo.data.lumber ? materialInfo.data.lumber : 0,
          icon: lumberIcon,
        },
        steel: {
          name: 'Steel',
          amount: materialInfo.data.steel ? materialInfo.data.steel : 0,
          icon: steelIcon,
        },
      };

      const machineHolder = {
        crane: {
          name: 'Crane',
          amount: machineInfo.data.crane ? machineInfo.data.crane : 0,
          icon: craneIcon,
        },
        drill: {
          name: 'Drill',
          amount: machineInfo.data.drill ? machineInfo.data.drill : 0,
          icon: drillIcon,
        },
        forklift: {
          name: 'Forklift',
          amount: machineInfo.data.forklift ? machineInfo.data.forklift : 0,
          icon: forkliftIcon,
        },
        ladder: {
          name: 'Ladder',
          amount: machineInfo.data.ladder ? machineInfo.data.ladder : 0,
          icon: ladderIcon,
        },
      };
      const updatedMaxMaterialAmount = {
        brick: Math.min(parseInt(materialWarehouseInfo.data.brick), 1000),
        cement: Math.min(parseInt(materialWarehouseInfo.data.cement), 1000),
        lumber: Math.min(parseInt(materialWarehouseInfo.data.lumber), 1000),
        steel: Math.min(parseInt(materialWarehouseInfo.data.steel), 1000),
      };

      const updatedMaxMachineAmount = {
        crane: Math.min(parseInt(machineWarehouseInfo.data.crane), 30),
        drill: Math.min(parseInt(machineWarehouseInfo.data.drill), 30),
        forklift: Math.min(parseInt(machineWarehouseInfo.data.forklift), 30),
        ladder: Math.min(parseInt(machineWarehouseInfo.data.ladder), 30),
      };
      setMaterials(materialHolder);
      setMachines(machineHolder);
      setMaxMaterialAmount(updatedMaxMaterialAmount);
      setMaxMachineAmount(updatedMaxMachineAmount);
      setMaterialsId(materialInfo.data.id);
      setMachinesId(machineInfo.data.id);
    }
    fetchData();
  }, []);

  const handleReturnMachinesToProject = async (yesOrNo) => {
    if (yesOrNo) {
      const toReturnMachines = await MachineryAPI.getMachineryByProjectId(
        projectId
      );
      await MachineryAPI.returnMachineryForProject(toReturnMachines.data);
      setMachines({
        crane: {
          name: 'Crane',
          amount: 0,
          icon: craneIcon,
        },
        drill: {
          name: 'Drill',
          amount: 0,
          icon: drillIcon,
        },
        forklift: {
          name: 'Forklift',
          amount: 0,
          icon: forkliftIcon,
        },
        ladder: {
          name: 'Ladder',
          amount: 0,
          icon: ladderIcon,
        },
      });
      const updatedMaxMachineAmount = {
        crane: maxMachineAmount.crane + parseFloat(toReturnMachines.crane),
        drill: maxMachineAmount.drill + parseFloat(toReturnMachines.drill),
        forklift:
          maxMachineAmount.forklift + parseInt(toReturnMachines.forklift),
        ladder: maxMachineAmount.ladder + parseFloat(toReturnMachines.ladder),
      };
      setMaxMachineAmount(updatedMaxMachineAmount);
      alert('Machines Returned');
    } else {
    }
  };

  const handleAddMachinesToProject = (machinesInformation) => {
    const updatedMachineInformation = {
      crane: parseFloat(machinesInformation.crane),
      drill: parseFloat(machinesInformation.drill),
      forklift: parseFloat(machinesInformation.forklift),
      ladder: parseFloat(machinesInformation.ladder),
      id: machineId,
      projectId: machinesInformation.projectId,
    };

    MachineryAPI.requestMachineryForProject(updatedMachineInformation);

    const updatedMaxMachineAmount = {
      crane: maxMachineAmount.crane - parseFloat(machinesInformation.crane),
      drill: maxMachineAmount.drill - parseFloat(machinesInformation.drill),
      forklift: maxMachineAmount.forklift - parseInt(machinesInformation.forklift),
      ladder: maxMachineAmount.ladder - parseFloat(machinesInformation.ladder),
    };
    setMaxMachineAmount(updatedMaxMachineAmount);
    const updatedMachine = {
      crane: {
        ...machines.crane,
        amount: machines.crane.amount + parseFloat(machinesInformation.crane),
      },
      drill: {
        ...machines.drill,
        amount: machines.drill.amount + parseFloat(machinesInformation.drill),
      },
      forklift: {
        ...machines.forklift,
        amount:
          machines.forklift.amount + parseFloat(machinesInformation.forklift),
      },
      ladder: {
        ...machines.ladder,
        amount: machines.ladder.amount + parseFloat(machinesInformation.ladder),
      },
    };
    setMachines(updatedMachine);
    alert('Machines added');
  };

  const handleAddMaterialsToProject = (materialInformation) => {
    const updatedMaterialsInformation = {
      brick: parseFloat(materialInformation.brick),
      cement: parseFloat(materialInformation.cement),
      lumber: parseFloat(materialInformation.lumber),
      steel: parseFloat(materialInformation.steel),
      id: materialsId,
      projectId: materialInformation.projectId,
    };

    MaterialAPI.requestMaterialsForProject(updatedMaterialsInformation);

    const updatedMaxMaterialAmount = {
      brick: maxMaterialAmount.brick - parseFloat(materialInformation.brick),
      cement: maxMaterialAmount.cement - parseFloat(materialInformation.cement),
      lumber: maxMaterialAmount.lumber - parseInt(materialInformation.lumber),
      steel: maxMaterialAmount.steel - parseFloat(materialInformation.steel),
    };
    setMaxMaterialAmount(updatedMaxMaterialAmount);
    const updatedMaterial = {
      brick: {
        ...materials.brick,
        amount: materials.brick.amount + parseFloat(materialInformation.brick),
      },
      cement: {
        ...materials.cement,
        amount:
          materials.cement.amount + parseFloat(materialInformation.cement),
      },
      lumber: {
        ...materials.lumber,
        amount:
          materials.lumber.amount + parseFloat(materialInformation.lumber),
      },
      steel: {
        ...materials.steel,
        amount: materials.steel.amount + parseFloat(materialInformation.steel),
      },
    };
    setMaterials(updatedMaterial);
    alert('resource added');
  };

  return (
    <div>
      <h3 className='mb-3'>Resources</h3>
      <div
        className='resourcesWrapper row mt-3'
        style={{
          minHeight: '100px',
          minWidth: '275px',
        }}
      >
        {materials &&
          Object.entries(materials).map(([key, value]) => {
            return (
              <div className='col-3' key={value.name}>
                <div className='materialOrMachineryIcon'>
                  <p className='textElementMaterials'>{value.name}</p>
                  <img
                    src={value.icon}
                    width='60'
                    height='60'
                    alt={value.name}
                  ></img>
                  <p className='textElementMaterials'>{value.amount}</p>
                </div>
              </div>
            );
          })}
      </div>
      {hasAuthority && (
        <div>
          <button
            type='button'
            className='btn btn-outline-warning'
            data-bs-toggle='modal'
            data-bs-target='#addMaterialsModal'
            disabled={!hasAuthority}
            style={{ width: "50%" }}
          >
            Add Materials
          </button>
          <AddMaterialModal
            maxMaterialAmount={maxMaterialAmount}
            modalId='addMaterialsModal'
            handleAddMaterialsToProject={handleAddMaterialsToProject}
            hasAuthority={hasAuthority}
            projectId={projectId}
          ></AddMaterialModal>
        </div>
      )}
      <div
        className='resourcesWrapper row mt-3'
        style={{
          minHeight: '100px',
          minWidth: '275px',
        }}
      >
        {machines &&
          Object.entries(machines).map(([key, value]) => {
            return (
              <div className='col-3' key={value.name}>
                <div className='materialOrMachineryIcon'>
                  <p className='textElementMaterials'>{value.name}</p>
                  <img
                    src={value.icon}
                    width='60'
                    height='60'
                    alt={value.name}
                  ></img>
                  <p className='textElementMaterials'>{value.amount}</p>
                </div>
              </div>
            );
          })}
      </div>
      <AddMachineModal
        maxMachineAmount={maxMachineAmount}
        modalId='addMachinesModal'
        handleAddMachinesToProject={handleAddMachinesToProject}
        hasAuthority={hasAuthority}
        projectId={projectId}
      ></AddMachineModal>
      {hasAuthority && (
        <div>
          <div style={{ display: "flex", justifyContent: "center", marginBottom: "5%" }}>
            <button
              type='button'
              className='btn btn-outline-warning'
              data-bs-toggle='modal'
              data-bs-target='#addMachinesModal'
              disabled={!hasAuthority}
              style={{ marginRight: "0.5%", width: "25%" }}
            >
              Add Machines
            </button>
            <button
              type='button'
              className='btn btn-outline-warning'
              data-bs-toggle='modal'
              data-bs-target='#confirmReturnMachinesModal'
              disabled={!hasAuthority}
              style={{ marginLeft: "0.5%", width: "25%" }}
            >
              Return Machines
            </button>
          </div>

          <ConfirmReturnMachinesModal
            handleReturnMachinesToProject={handleReturnMachinesToProject}
            hasAuthority={hasAuthority}
            modalId='confirmReturnMachinesModal'
          ></ConfirmReturnMachinesModal>
        </div>
      )}
    </div>
  );
}
