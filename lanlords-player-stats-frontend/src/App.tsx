import "./App.css";
import { TeamPage } from "./pages/TeamPage";
import { fieldFuckerMembers } from "./types/Player";
import { fieldFuckers } from "./types/Team";

function App() {
  return (
    <div className="App">
      <div className="card-container">
        <TeamPage team={fieldFuckers} members={fieldFuckerMembers} />
      </div>
    </div>
  );
}

export default App;
