import personImg from "../assets/person.jfif";
import { Game } from "./Game";
import { fieldFuckers, Team } from "./Team";

export type Player = {
  id: number;
  name: string;
  handle: string;
  img: string;
  team: Team;
  scores: Scores;
};

export type PlayerProp = {
  player: Player;
};

// ELO implementation based on a range of 0 - 5000
// Average is 2500
type Scores = {
  [K in Game]?: number;
};

export const pirokiko: Player = {
  id: 1,
  name: "Milan de Graaf",
  handle: "Pirokiko",
  team: fieldFuckers,
  img: personImg,
  scores: {
    BF1942: 1750,
    BF2142: 2000,
    COD2: 2500,
    COD4: 2750,
    COD5: 2750,
    TF2: 2600,
    CSGO: 2750,
  },
};

export const fieldFuckerMembers = [
  pirokiko,
  pirokiko,
  pirokiko,
  pirokiko,
  pirokiko,
  pirokiko,
  pirokiko,
  pirokiko,
];

export const elo = (player: Player) => {
  const elos = Object.values(player.scores);
  const total = elos.reduce((a, b) => a + b, 0);
  return Math.floor(total / elos.length);
};
