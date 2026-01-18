import {logs} from "../data/logs";

const Logs = ()=>{
    const highImpactLogs=logs.filter(
        log=>log.carbon>=4
    );
    return 
    (
        <div>
            <h2> Daily Logs</h2>

            <ul>
                {highImpactLogs.map(log =>(
                    <li key ={log.id}>
                        {log.activity}- {log.carbon} kg
                    </li>
                ) )}
            </ul>
        </div>
    )
};
export default Logs;