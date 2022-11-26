import logo from "../assets/Team_Liquid.png";
import { Player } from "./Player";

export type Team = {
  id: number;
  name: string;
  img: string;
};

export type TeamProp = {
  team: Team;
};

export type TeamMembersProps = {
  members: Player[];
};

export const fieldFuckers: Team = {
  id: 1,
  name: "FieldFuckers",
  img: logo,
};
