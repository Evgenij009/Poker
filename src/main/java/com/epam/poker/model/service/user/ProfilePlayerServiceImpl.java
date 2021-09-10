package com.epam.poker.model.service.user;

import com.epam.poker.exception.DaoException;
import com.epam.poker.exception.ServiceException;
import com.epam.poker.model.dao.AbstractDao;
import com.epam.poker.model.dao.helper.DaoSaveTransaction;
import com.epam.poker.model.dao.impl.user.ProfilePlayerDao;
import com.epam.poker.model.dao.impl.user.ProfilePlayerDaoImpl;
import com.epam.poker.model.entity.ProfilePlayer;
import com.epam.poker.model.validator.Validator;
import com.epam.poker.model.validator.impl.ProfilePlayerValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProfilePlayerServiceImpl implements ProfilePlayerService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static Validator<ProfilePlayer> profilePlayerValidator = ProfilePlayerValidator.getInstance();
    private static ProfilePlayerService instance;

    public static ProfilePlayerService getInstance() {
        if (instance == null) {
            instance = new ProfilePlayerServiceImpl();
        }
        return instance;
    }

    @Override
    public int findProfilePlayerAmount() throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            int answer = profilePlayerDao.findProfilePlayerAmount();
            return answer;
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<ProfilePlayer> findAll() throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            List<ProfilePlayer> profilePlayerList = profilePlayerDao.findAll();
            return profilePlayerList;
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public ProfilePlayer findProfilePlayerById(long id) throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            Optional<ProfilePlayer> profilePlayer = profilePlayerDao.findById(id);
            if (!profilePlayer.isPresent()) {
                throw new ServiceException("Profile player id=" + id + " is not found");
            }
            return profilePlayer.get();
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean updateProfilePlayerByUserId(long userId, ProfilePlayer profilePlayer) throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            return profilePlayerDao.updateProfilePlayerByUserId(userId, profilePlayer);
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean updatePhotoByUserId(long userId, String photo) throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            return profilePlayerDao.updatePhotoByUserId(userId, photo);
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean updateBestPrizeByUserId(long userId, BigDecimal bestPrize) throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            return profilePlayerDao.updateBestPrizeByUserId(userId, bestPrize);
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean updateAwardByUserId(long userId, String award) throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            return profilePlayerDao.updateAwardByUserId(userId, award);
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean updateAboutYourselfByUserId(long userId, String aboutYourself) throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            return profilePlayerDao.updateAboutYourselfByUserId(userId, aboutYourself);
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean updateLostMoneyByUserId(long userId, BigDecimal money) throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            return profilePlayerDao.updateLostMoneyByUserId(userId, money);
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean updateWinMoneyByUserId(long userId, BigDecimal money) throws ServiceException, DaoException {
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            return profilePlayerDao.updateWinMoneyByUserId(userId, money);
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void add(ProfilePlayer profilePlayer) throws ServiceException, DaoException {
        if (!profilePlayerValidator.isValid(profilePlayer)) {
            throw new ServiceException("Invalid profile player data.");
        }
        DaoSaveTransaction transaction = new DaoSaveTransaction();
        try {
            ProfilePlayerDao profilePlayerDao = ProfilePlayerDaoImpl.getInstance();
            transaction.init((AbstractDao) profilePlayerDao);
            profilePlayerDao.add(profilePlayer);
        } catch (DaoException e) {
            LOGGER.error("Transaction error: " + e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}