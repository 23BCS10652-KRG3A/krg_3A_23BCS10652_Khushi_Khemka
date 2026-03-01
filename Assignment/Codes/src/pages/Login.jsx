import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    if (userId === "admin" && password === "1234") {
      localStorage.setItem("token", "true");
      navigate("/dashboard");
    } else {
      setError("Invalid ID or Password");
    }
  };

  return (
    <div className="center-container">
      <div className="card">
        <h2>EcoTrack Login</h2>

        <form onSubmit={handleLogin}>
          <input
            className="input"
            type="text"
            placeholder="User ID"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            required
          />

          <input
            className="input"
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          {error && <p className="error">{error}</p>}

          <button type="submit" className="primary-btn">
            Login
          </button>
        </form>

        <p style={{ marginTop: "15px", fontSize: "13px", color: "#cbd5e1" }}>
          Demo: admin / 1234
        </p>
      </div>
    </div>
  );
}

export default Login;