import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity()
export class TeamLeader {
    @PrimaryGeneratedColumn()
    id: number;
    @Column()
    name: string;
    @Column()
    lastname: string;
    @Column()
    age: number;
}