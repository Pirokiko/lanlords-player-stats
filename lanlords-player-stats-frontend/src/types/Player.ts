import logo from "../assets/virginia_cavaliers.png";
import personImg from "../assets/person.jfif";

export type Team = {
  id: number;
  name: string;
  img: string;
};

export type Player = {
  id: number;
  name: string;
  handle: string;
  img: string;
  team: Team;
  scores: Scores;
};

enum Games {
  BF1942 = "BF1942",
  BF2142 = "BF2142",
  COD2 = "COD2",
  COD4 = "COD4",
  COD5 = "COD5",
  TF2 = "TF2",
  CSGO = "CSGO",
}

// ELO implementation based on a range of 0 - 5000
// Average is 2500
type Scores = {
  [K in Games]?: number;
};

export const fieldFuckers: Team = {
  id: 1,
  name: "FieldFuckers",
  img: logo,
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
