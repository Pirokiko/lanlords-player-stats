import { Box, Card, CardContent, CardHeader, Grid } from "@mui/material";
import { Fragment } from "react";
import { FC } from "react";
import { Game } from "../../types/Game";
import { elo, Player, PlayerProp } from "../../types/Player";

const styles = {
  logo: {
    maxHeight: 40,
    display: "block",
  },
  header: {
    maxHeight: 80,
    paddingBottom: 0,
  },
  card: {},
  playerImg: {
    display: "block",
    maxWidth: "100%",
  },
} as const;

const PlayerHeader: FC<PlayerProp> = ({ player }) => (
  <Grid container>
    <Grid item xs={2}>
      <img src={player.team.img} style={styles.logo} alt="team image" />
    </Grid>
    <Grid item xs={10} display="flex" alignItems="center" pl={1}>
      {player.handle} | {player.name}
    </Grid>
  </Grid>
);

export const PlayerCard: FC<PlayerProp> = ({ player }) => (
  <Card variant="elevation" raised sx={styles.card}>
    <CardHeader title={<PlayerHeader player={player} />} sx={styles.header} />
    <Box
      component={CardContent}
      display="grid"
      columnGap={1}
      gridTemplateColumns="100px auto"
    >
      <div>
        <img src={player.img} style={styles.playerImg} alt="player image" />
        <div>MMR</div>
        <div>{elo(player)}</div>
      </div>
      <ScoreListing player={player} />
    </Box>
  </Card>
);

const ScoreListing: FC<PlayerProp> = ({ player: { scores } }) => {
  const keys = Object.keys(scores) as Game[];
  const half = Math.ceil(keys.length / 2);
  const first = keys.slice(0, half);
  const second = keys.slice(half);

  return (
    <Grid container>
      <Grid item xs={6}>
        <ScoresList scores={scores} games={first} />
      </Grid>
      <Grid item xs={6}>
        <ScoresList scores={scores} games={second} />
      </Grid>
    </Grid>
  );
};

const ScoresList: FC<{ scores: Player["scores"]; games: Game[] }> = ({
  scores,
  games,
}) => {
  return (
    <Grid container columnSpacing={1}>
      {games.map((game) => (
        <Fragment key={game}>
          <Grid item xs={6} textAlign="end">
            {game}
          </Grid>
          <Grid item xs={6} textAlign="start">
            {scores[game]}
          </Grid>
        </Fragment>
      ))}
    </Grid>
  );
};
