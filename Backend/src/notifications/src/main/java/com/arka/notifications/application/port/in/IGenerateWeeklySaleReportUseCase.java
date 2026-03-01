package com.arka.notifications.application.port.in;

import com.arka.notifications.application.useCase.command.GenerateWeeklyReportCommand;

public interface IGenerateWeeklySaleReportUseCase {
    public void execute (GenerateWeeklyReportCommand cmd);
}
