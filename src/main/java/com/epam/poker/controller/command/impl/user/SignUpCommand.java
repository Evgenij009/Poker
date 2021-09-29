package com.epam.poker.controller.command.impl.user;

import com.epam.poker.controller.command.Command;
import com.epam.poker.controller.command.CommandResult;
import com.epam.poker.controller.command.constant.Attribute;
import com.epam.poker.controller.command.constant.CommandName;
import com.epam.poker.controller.command.constant.PagePath;
import com.epam.poker.controller.command.constant.Parameter;
import com.epam.poker.controller.command.util.ParameterTaker;
import com.epam.poker.controller.request.RequestContext;
import com.epam.poker.exception.DaoException;
import com.epam.poker.exception.InvalidParametersException;
import com.epam.poker.exception.ServiceException;
import com.epam.poker.model.entity.ProfilePlayer;
import com.epam.poker.model.entity.User;
import com.epam.poker.model.entity.type.UserRole;
import com.epam.poker.model.entity.type.UserStatus;
import com.epam.poker.model.service.user.SignUpService;
import com.epam.poker.model.service.user.impl.SignUpServiceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SignUpCommand implements Command {
    private static final String LOGIN_PAGE_COMMAND = "poker?command=" + CommandName.LOGIN_PAGE;
    private static final BigDecimal INITIAL_BALANCE_USER = BigDecimal.valueOf(198.99);
    private static final String USERNAME_EXIST_KEY = "username.exist";
    private static final String INVALID_DATA_KEY = "invalid.data";
    private static final BigDecimal PRE_MONEY = BigDecimal.valueOf(0);
    private static final String PRE_AWARD = "Your future award";
    private static final String PRE_ABOUT_YOURSELF = "Write about yourself";
    private static final String PRE_PHOTO = "notAva.jpg";
    private SignUpService sigUpService = SignUpServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException, InvalidParametersException {
        String login = ParameterTaker.takeString(Parameter.LOGIN, requestContext);
        String email = ParameterTaker.takeString(Parameter.EMAIL, requestContext);
        boolean isLoginAndEmailExist = false;
        try {
            isLoginAndEmailExist = sigUpService.isUserLoginExist(login) && sigUpService.isUserEmailExist(email);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (!isLoginAndEmailExist) {
            requestContext.addAttribute(Attribute.SAVED_LOGIN, login);
            requestContext.addAttribute(Attribute.SAVED_EMAIL, email);
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            User user = User.builder()
                    .setLogin(login)
                    .setPassword(ParameterTaker.takeString(Parameter.PASSWORD, requestContext))
                    .setFirstName(ParameterTaker.takeString(Parameter.FIRST_NAME, requestContext))
                    .setLastName(ParameterTaker.takeString(Parameter.LAST_NAME, requestContext))
                    .setEmail(ParameterTaker.takeString(Parameter.EMAIL, requestContext))
                    .setBalance(INITIAL_BALANCE_USER)
                    .setUserRole(UserRole.USER)
                    .setUserStatus(UserStatus.ACTIVE)
                    .setPhoneNumber(ParameterTaker.takePhoneNumber(Parameter.PHONE_NUMBER, requestContext))
                    .setCreateTime(nowTime)
                    .createUser();
            ProfilePlayer profilePlayer = ProfilePlayer.builder()
                    .setBestPrize(PRE_MONEY)
                    .setAward(PRE_AWARD)
                    .setPhoto(PRE_PHOTO)
                    .setAboutYourself(PRE_ABOUT_YOURSELF)
                    .setLostMoney(PRE_MONEY)
                    .setWinMoney(PRE_MONEY)
                    .createRatingPlayer();
            long idUser = 0;
            try {
                idUser = sigUpService.signUp(user, profilePlayer);
            } catch (DaoException e) {
                e.printStackTrace();
            }
            if (idUser != 0) {
                return CommandResult.redirect(LOGIN_PAGE_COMMAND);
            } else {
                requestContext.addAttribute(Attribute.ERROR_MESSAGE, INVALID_DATA_KEY);
            }
        } else {
            requestContext.addAttribute(Attribute.ERROR_MESSAGE, USERNAME_EXIST_KEY);
        }
        return CommandResult.forward(PagePath.SIGN_UP);
    }
}
