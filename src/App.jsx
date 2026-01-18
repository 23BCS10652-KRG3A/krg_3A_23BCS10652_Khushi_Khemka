import Header from "./components/Header";
import Logs from "./pages/logs";
import Dashboard from "./pages/dashboard";

const App=()=>{
  return(
   <>
   <Header title= "Ecotrack - Experiment 1"/>
    <main style={{padding: "1 rem"}}>
   <Dashboard/>
   <Logs/>
   </main>
   </>
  );
};
export default App;