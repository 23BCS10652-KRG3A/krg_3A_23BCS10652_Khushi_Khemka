import { useState, useEffect, useCallback, useMemo } from "react";
import Navbar from "../components/Navbar";
import CounterDisplay from "../components/CounterDisplay";

function WaterTracker() {
  const [count, setCount] = useState(0);
  const [goal, setGoal] = useState(8);
  const [goalInput, setGoalInput] = useState(8);
  const [tip, setTip] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  /* ---------------- LocalStorage ---------------- */
  useEffect(() => {
    const savedCount = localStorage.getItem("waterCount");
    const savedGoal = localStorage.getItem("waterGoal");

    if (savedCount) setCount(Number(savedCount));
    if (savedGoal) {
      setGoal(Number(savedGoal));
      setGoalInput(Number(savedGoal));
    }
  }, []);

  useEffect(() => {
    localStorage.setItem("waterCount", count);
  }, [count]);

  useEffect(() => {
    localStorage.setItem("waterGoal", goal);
  }, [goal]);

  /* ---------------- API ---------------- */
  useEffect(() => {
    const fetchTip = async () => {
      setLoading(true);
      try {
        const res = await fetch("https://api.adviceslip.com/advice");
        const data = await res.json();
        setTip(data.slip.advice);
      } catch {
        setError("Failed to load health tip.");
      } finally {
        setLoading(false);
      }
    };
    fetchTip();
  }, []);

  /* ---------------- Actions ---------------- */
  const increment = useCallback(() => {
    setCount((prev) => prev + 1);
  }, []);

  const decrement = useCallback(() => {
    setCount((prev) => (prev > 0 ? prev - 1 : 0));
  }, []);

  const reset = useCallback(() => {
    setCount(0);
  }, []);

  const saveGoal = () => {
    if (goalInput > 0) setGoal(goalInput);
  };

  /* ---------------- Progress Calculation ---------------- */
  const progress = useMemo(() => {
    return Math.min((count / goal) * 100, 100);
  }, [count, goal]);

  return (
    <>
      <Navbar />

      <div className="center-container">
        <div className="card">
          <h2>Water Intake Tracker 💧</h2>

          <CounterDisplay count={count} />

          {/* Buttons */}
          <div>
            <button className="secondary-btn btn-blue" onClick={increment}>+</button>
            <button className="secondary-btn btn-red" onClick={decrement}>-</button>
            <button className="secondary-btn btn-gray" onClick={reset}>Reset</button>
          </div>

          {/* Progress Text */}
          <p className="progress">
            {count} / {goal} glasses completed
          </p>

          {/* Progress Bar */}
          <div className="progress-bar">
            <div
              className="progress-fill"
              style={{ width: `${progress}%` }}
            ></div>
          </div>

          {count >= goal && (
            <p className="goal-text animate-pop">🎉 Goal Reached!</p>
          )}

          {/* Glass Icons */}
          <div className="glass-container">
            {[...Array(goal)].map((_, i) => (
              <div
                key={i}
                className={`glass ${i < count ? "filled" : ""}`}
              >
                💧
              </div>
            ))}
          </div>

          {/* Goal Input */}
          <div style={{ marginTop: "20px" }}>
            <input
              type="number"
              value={goalInput}
              onChange={(e) => setGoalInput(Number(e.target.value))}
              className="input"
              placeholder="Set Daily Goal"
            />
            <button className="primary-btn" onClick={saveGoal}>
              Save Goal
            </button>
          </div>

          {/* Tip Box */}
          <div className="tip-box">
            <h4>Today's Health Tip</h4>
            {loading && <p>Loading...</p>}
            {error && <p className="error">{error}</p>}
            {!loading && !error && <p>{tip}</p>}
          </div>
        </div>
      </div>
    </>
  );
}

export default WaterTracker;