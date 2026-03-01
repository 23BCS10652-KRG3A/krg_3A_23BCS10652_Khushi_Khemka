import Navbar from "../components/Navbar";

function Dashboard() {
  return (
    <>
      <Navbar />
      <div className="center-container">
        <div className="card">
          <h2>Welcome to EcoTrack 🌿</h2>
          <p style={{ marginTop: "15px", color: "#cbd5e1" }}>
            Track your daily water intake and stay healthy.
          </p>
        </div>
      </div>
    </>
  );
}

export default Dashboard;