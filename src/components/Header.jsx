const Header = ({ title }) => {
  return (
    <header
      style={{
        padding: "0.5rem",
        backgroundColor: "#27ae60",
        color: "white",
      }}
    >
      <h1>{title}</h1>
    </header>
  );
};

export default Header;
