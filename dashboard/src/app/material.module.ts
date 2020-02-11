import { NgModule } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatMenuModule } from "@angular/material/menu";
import { MatCardModule } from "@angular/material/card";
import { MatIconModule } from "@angular/material/icon";

@NgModule({
  imports: [MatCardModule, MatMenuModule, MatIconModule, MatButtonModule],
  exports: [MatCardModule, MatMenuModule, MatIconModule, MatButtonModule]
})
export class MaterialModule {}
