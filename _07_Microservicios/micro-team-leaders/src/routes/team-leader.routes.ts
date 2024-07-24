import { Router } from "express";
import { getAll } from "../controllers/team-leader.controller";

const teamLeaderRoutes = Router();

teamLeaderRoutes.get("/", getAll)

export default teamLeaderRoutes;