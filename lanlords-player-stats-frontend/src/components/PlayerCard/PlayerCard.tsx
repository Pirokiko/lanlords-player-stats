import {
  Box,
  Card,
  CardContent,
  CardHeader,
  Grid,
  List,
  ListItem,
} from "@mui/material";
import { FC } from "react";
import { Player } from "../../types/Player";

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

type PlayerProp = {
  player: Player;
};

const PlayerHeader: FC<PlayerProp> = ({ player }) => (
  <Grid container>
    <Grid item xs={1}>
      <img src={player.team.img} style={styles.logo} alt="team image" />
    </Grid>
    <Grid item xs={11} display="flex" alignItems="center" pl={1}>
      {player.name}
    </Grid>
  </Grid>
);

export const PlayerCard: FC<PlayerProp> = ({ player }) => (
  <Card variant="elevation" raised sx={styles.card}>
    <CardHeader
      title={<PlayerHeader player={player} />}
      dividers
      sx={styles.header}
    />
    <Box
      component={CardContent}
      display="grid"
      columnGap={1}
      gridTemplateColumns="100px auto"
    >
      <div>
        <img src={player.img} style={styles.playerImg} alt="player image" />
        <div>MMR</div>
        <div>2500</div>
      </div>
      <Grid container>
        <Grid item xs={6}>
          <List dense>
            <ListItem>1942: 250</ListItem>
            <ListItem>BF2: 250</ListItem>
            <ListItem>TF2: 250</ListItem>
          </List>
        </Grid>
        <Grid item xs={6}>
          <List dense>
            <ListItem>COD2: 250</ListItem>
            <ListItem>COD4: 250</ListItem>
            <ListItem>CSGO: 250</ListItem>
          </List>
        </Grid>
      </Grid>
    </Box>
  </Card>
);
