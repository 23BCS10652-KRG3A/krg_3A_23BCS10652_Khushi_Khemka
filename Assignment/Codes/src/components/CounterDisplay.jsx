import React from "react";

function CounterDisplay({ count }) {
  console.log("CounterDisplay Rendered");
  return <h3>{count} glasses</h3>;
}

export default React.memo(CounterDisplay);