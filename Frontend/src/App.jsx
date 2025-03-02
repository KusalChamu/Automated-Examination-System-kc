import { Button, Container, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";

function App() {
  const navigate = useNavigate();

  return (
    <Container maxWidth="sm">
      <Typography variant="h3" gutterBottom>
        Welcome to My App
      </Typography>
      <Button variant="contained" color="primary" onClick={() => navigate("/login")}>
        Go to Login
      </Button>
    </Container>
  );
}

export default App;
