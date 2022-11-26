import {
  Box,
  Card,
  CardContent,
  CardHeader,
  CardMedia,
  Divider,
  Grid,
} from "@mui/material";
import { FC } from "react";
import { PlayerCard } from "../components/PlayerCard/PlayerCard";
import { elo } from "../types/Player";
import { TeamMembersProps, TeamProp } from "../types/Team";

const MemberListing: FC<TeamMembersProps> = ({ members }) => (
  <Grid container spacing={1}>
    {members.map((player) => (
      <Grid key={`${Math.random()}`} item xs={4}>
        <PlayerCard player={player} />
      </Grid>
    ))}
  </Grid>
);

export const TeamPage: FC<TeamProp & TeamMembersProps> = ({
  team,
  members,
}) => (
  <Card>
    <CardHeader title={team.name} />
    <Divider />
    <Box component={CardContent} display="flex">
      <CardMedia
        component="img"
        sx={{ width: 150 }}
        image={team.img}
        alt="team logo"
      />
      <Box
        component="div"
        flexGrow={1}
        justifyContent="start"
        textAlign="start"
        paddingLeft={2}
      >
        <div>ID: {team.id}</div>
        <div>Sinds: 01-01-1970</div>
        <div>Members: {members.length}</div>
        <div>
          Average ELO:{" "}
          {members.map(elo).reduce((a, b) => a + b, 0) / members.length}
        </div>
        <div>Captain: Thunderbuff | Jona</div>
      </Box>
    </Box>
    <Divider />
    <CardHeader title="Members" />
    <Divider />
    <CardContent>
      <MemberListing members={members} />
    </CardContent>
  </Card>
);
