#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BSet.cpp"
#include "BObject.cpp"
#include "BBoolean.cpp"
#include "BInteger.cpp"

#ifndef Cruise_finite_k_H
#define Cruise_finite_k_H

using namespace std;

class Cruise_finite_k {

    class RSset : public BObject {
        public:

            enum RSset_type {
                RSnone, 
                RSpos, 
                RSneg, 
                RSequal
            };

            RSset_type value;

            RSset(){}

            RSset(RSset_type type) {
                this->value = type;
            }

            BBoolean equal(const RSset& o) {
                return value == o.value;
            }

            BBoolean unequal(const RSset& o) {
                return value != o.value;
            }

            friend bool operator ==(const RSset& p1, const RSset& p2) {
                return p1.value == p2.value;
            }

            friend bool operator !=(const RSset& p1, const RSset& p2) {
                return p1.value != p2.value;
            }

            int hashCode() const {
                return static_cast<int>(value);
            }
    };

    class ODset : public BObject {
        public:

            enum ODset_type {
                ODnone, 
                ODclose, 
                ODveryclose
            };

            ODset_type value;

            ODset(){}

            ODset(ODset_type type) {
                this->value = type;
            }

            BBoolean equal(const ODset& o) {
                return value == o.value;
            }

            BBoolean unequal(const ODset& o) {
                return value != o.value;
            }

            friend bool operator ==(const ODset& p1, const ODset& p2) {
                return p1.value == p2.value;
            }

            friend bool operator !=(const ODset& p1, const ODset& p2) {
                return p1.value != p2.value;
            }

            int hashCode() const {
                return static_cast<int>(value);
            }
    };


    private:


        #define _RSset (BSet<RSset >((std::vector<RSset>){(RSset(RSset::RSnone)), (RSset(RSset::RSpos)), (RSset(RSset::RSneg)), (RSset(RSset::RSequal))}))
        #define _ODset (BSet<ODset >((std::vector<ODset>){(ODset(ODset::ODnone)), (ODset(ODset::ODclose)), (ODset(ODset::ODveryclose))}))

        BBoolean CruiseAllowed;
        BBoolean CruiseActive;
        BBoolean VehicleAtCruiseSpeed;
        BBoolean VehicleCanKeepSpeed;
        BBoolean VehicleTryKeepSpeed;
        BBoolean SpeedAboveMax;
        BBoolean VehicleTryKeepTimeGap;
        BBoolean CruiseSpeedAtMax;
        BBoolean ObstaclePresent;
        ODset ObstacleDistance;
        RSset ObstacleRelativeSpeed;
        BBoolean ObstacleStatusJustChanged;
        BBoolean CCInitialisationInProgress;
        BBoolean CruiseSpeedChangeInProgress;
        BInteger NumberOfSetCruise;

        bool initialized = false;

    public:

        Cruise_finite_k(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            BBoolean _ld_CruiseAllowed = static_cast<BBoolean >(CruiseAllowed);
            BBoolean _ld_CruiseActive = static_cast<BBoolean >(CruiseActive);
            BBoolean _ld_VehicleAtCruiseSpeed = static_cast<BBoolean >(VehicleAtCruiseSpeed);
            BBoolean _ld_VehicleCanKeepSpeed = static_cast<BBoolean >(VehicleCanKeepSpeed);
            BBoolean _ld_VehicleTryKeepSpeed = static_cast<BBoolean >(VehicleTryKeepSpeed);
            BBoolean _ld_SpeedAboveMax = static_cast<BBoolean >(SpeedAboveMax);
            BBoolean _ld_VehicleTryKeepTimeGap = static_cast<BBoolean >(VehicleTryKeepTimeGap);
            BInteger _ld_NumberOfSetCruise = static_cast<BInteger >(NumberOfSetCruise);
            BBoolean _ld_CruiseSpeedAtMax = static_cast<BBoolean >(CruiseSpeedAtMax);
            ODset _ld_ObstacleDistance = static_cast<ODset >(ObstacleDistance);
            BBoolean _ld_ObstacleStatusJustChanged = static_cast<BBoolean >(ObstacleStatusJustChanged);
            BBoolean _ld_CCInitialisationInProgress = static_cast<BBoolean >(CCInitialisationInProgress);
            BBoolean _ld_CruiseSpeedChangeInProgress = static_cast<BBoolean >(CruiseSpeedChangeInProgress);
            for(int _0_i = 0; _0_i < ((BOOL).size()); _0_i++) {
                BBoolean op = static_cast<BBoolean >(*(std::next((BOOL).begin(), _0_i)));
                for(int _1_i = 0; _1_i < (_RSset.size()); _1_i++) {
                    RSset ors = static_cast<RSset >(*(std::next(_RSset.begin(), _1_i)));
                    if((BOOL).elementOf(op)._and(_RSset.elementOf(ors))._and(op.equal((BBoolean(false))).equivalent(ors.equal((RSset(RSset::RSnone))))).booleanValue()) {
                        BBoolean _ld_ObstaclePresent = static_cast<BBoolean >(ObstaclePresent);
                        RSset _ld_ObstacleRelativeSpeed = static_cast<RSset >(ObstacleRelativeSpeed);
                        ObstaclePresent = static_cast<BBoolean >(op);
                        ObstacleRelativeSpeed = static_cast<RSset >(ors);
                        break;
                    }
                }
            }
            CruiseAllowed = static_cast<BBoolean >((BBoolean(false)));
            CruiseActive = static_cast<BBoolean >((BBoolean(false)));
            VehicleAtCruiseSpeed = static_cast<BBoolean >((BBoolean(false)));
            VehicleCanKeepSpeed = static_cast<BBoolean >((BBoolean(false)));
            VehicleTryKeepSpeed = static_cast<BBoolean >((BBoolean(false)));
            SpeedAboveMax = static_cast<BBoolean >((BBoolean(false)));
            VehicleTryKeepTimeGap = static_cast<BBoolean >((BBoolean(false)));
            NumberOfSetCruise = static_cast<BInteger >((BInteger(0)));
            CruiseSpeedAtMax = static_cast<BBoolean >((BBoolean(false)));
            ObstacleDistance = static_cast<ODset >((ODset(ODset::ODnone)));
            ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(false)));
            CCInitialisationInProgress = static_cast<BBoolean >((BBoolean(false)));
            CruiseSpeedChangeInProgress = static_cast<BBoolean >((BBoolean(false)));
            initialized = true;
        }

        void CruiseBecomesNotAllowed() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CruiseAllowed.equal((BBoolean(true)))).booleanValue()) {
                BBoolean _ld_CruiseAllowed = static_cast<BBoolean >(CruiseAllowed);
                BBoolean _ld_CruiseActive = static_cast<BBoolean >(CruiseActive);
                BBoolean _ld_VehicleCanKeepSpeed = static_cast<BBoolean >(VehicleCanKeepSpeed);
                BBoolean _ld_VehicleTryKeepSpeed = static_cast<BBoolean >(VehicleTryKeepSpeed);
                BBoolean _ld_VehicleAtCruiseSpeed = static_cast<BBoolean >(VehicleAtCruiseSpeed);
                BBoolean _ld_VehicleTryKeepTimeGap = static_cast<BBoolean >(VehicleTryKeepTimeGap);
                BBoolean _ld_CruiseSpeedAtMax = static_cast<BBoolean >(CruiseSpeedAtMax);
                ODset _ld_ObstacleDistance = static_cast<ODset >(ObstacleDistance);
                BInteger _ld_NumberOfSetCruise = static_cast<BInteger >(NumberOfSetCruise);
                BBoolean _ld_ObstacleStatusJustChanged = static_cast<BBoolean >(ObstacleStatusJustChanged);
                BBoolean _ld_CCInitialisationInProgress = static_cast<BBoolean >(CCInitialisationInProgress);
                BBoolean _ld_CruiseSpeedChangeInProgress = static_cast<BBoolean >(CruiseSpeedChangeInProgress);
                CruiseAllowed = static_cast<BBoolean >((BBoolean(false)));
                CruiseActive = static_cast<BBoolean >((BBoolean(false)));
                VehicleCanKeepSpeed = static_cast<BBoolean >((BBoolean(false)));
                VehicleTryKeepSpeed = static_cast<BBoolean >((BBoolean(false)));
                VehicleAtCruiseSpeed = static_cast<BBoolean >((BBoolean(false)));
                VehicleTryKeepTimeGap = static_cast<BBoolean >((BBoolean(false)));
                CruiseSpeedAtMax = static_cast<BBoolean >((BBoolean(false)));
                ObstacleDistance = static_cast<ODset >((ODset(ODset::ODnone)));
                NumberOfSetCruise = static_cast<BInteger >((BInteger(0)));
                ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(false)));
                CCInitialisationInProgress = static_cast<BBoolean >((BBoolean(false)));
                CruiseSpeedChangeInProgress = static_cast<BBoolean >((BBoolean(false)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void CruiseBecomesAllowed() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CruiseAllowed.equal((BBoolean(false)))).booleanValue()) {
                CruiseAllowed = static_cast<BBoolean >((BBoolean(true)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void SetCruiseSpeed() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CruiseAllowed.equal((BBoolean(true)))).booleanValue()) {
                BBoolean _ld_CruiseActive = static_cast<BBoolean >(CruiseActive);
                BBoolean _ld_ObstacleStatusJustChanged = static_cast<BBoolean >(ObstacleStatusJustChanged);
                VehicleCanKeepSpeed = static_cast<BBoolean >((BOOL).nondeterminism());
                if((SpeedAboveMax.equal((BBoolean(false)))).booleanValue()) {
                    BBoolean _ld_VehicleAtCruiseSpeed = static_cast<BBoolean >(VehicleAtCruiseSpeed);
                    CruiseSpeedAtMax = static_cast<BBoolean >((BOOL).nondeterminism());
                    VehicleAtCruiseSpeed = static_cast<BBoolean >((BBoolean(true)));
                } else {
                    CruiseSpeedAtMax = static_cast<BBoolean >((BBoolean(true)));
                }
                if((CruiseActive.equal((BBoolean(true)))).booleanValue()) {
                    CruiseSpeedChangeInProgress = static_cast<BBoolean >((BBoolean(true)));
                } else {
                    CCInitialisationInProgress = static_cast<BBoolean >((BBoolean(true)));
                }
                if((NumberOfSetCruise.less((BInteger(10)))).booleanValue()) {
                    NumberOfSetCruise = static_cast<BInteger >(NumberOfSetCruise.plus((BInteger(1))));
                } 
                CruiseActive = static_cast<BBoolean >((BBoolean(true)));
                ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void CCInitialisationFinished() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CCInitialisationInProgress.equal((BBoolean(true)))).booleanValue()) {
                for(int _0_i = 0; _0_i < ((BOOL).size()); _0_i++) {
                    BBoolean vtks = static_cast<BBoolean >(*(std::next((BOOL).begin(), _0_i)));
                    for(int _1_i = 0; _1_i < ((BOOL).size()); _1_i++) {
                        BBoolean vtktg = static_cast<BBoolean >(*(std::next((BOOL).begin(), _1_i)));
                        if((BOOL).elementOf(vtks)._and((BOOL).elementOf(vtktg))._and(vtks.equal((BBoolean(true)))._or(vtktg.equal((BBoolean(true))))._or(ObstacleStatusJustChanged.equal((BBoolean(true))))._or(CruiseSpeedChangeInProgress.equal((BBoolean(true)))))._and(ObstaclePresent.equal((BBoolean(false))).implies(vtktg.equal((BBoolean(false)))))._and(ObstacleDistance.equal((ODset(ODset::ODnone))).implies(vtks.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODclose)))._and(ObstacleRelativeSpeed.unequal((RSset(RSset::RSpos))))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(vtktg.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODveryclose)))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(vtktg.equal((BBoolean(true)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))._and(ObstacleDistance.unequal((ODset(ODset::ODveryclose))))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(vtks.equal((BBoolean(true)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSequal)))._and(ObstacleDistance.equal((ODset(ODset::ODnone)))).implies(vtktg.equal((BBoolean(false)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSneg)))._and(ObstacleDistance.equal((ODset(ODset::ODnone)))).implies(vtktg.equal((BBoolean(false)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))._and(ObstacleDistance.unequal((ODset(ODset::ODveryclose)))).implies(vtktg.equal((BBoolean(false))))).booleanValue()) {
                            BBoolean _ld_VehicleTryKeepTimeGap = static_cast<BBoolean >(VehicleTryKeepTimeGap);
                            BBoolean _ld_VehicleTryKeepSpeed = static_cast<BBoolean >(VehicleTryKeepSpeed);
                            VehicleTryKeepTimeGap = static_cast<BBoolean >(vtktg);
                            VehicleTryKeepSpeed = static_cast<BBoolean >(vtks);
                            break;
                        }
                    }
                }
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void CCInitialisationDelayFinished() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CCInitialisationInProgress.equal((BBoolean(true)))._and(VehicleTryKeepSpeed.equal((BBoolean(true)))._or(VehicleTryKeepTimeGap.equal((BBoolean(true))))._or(ObstacleStatusJustChanged.equal((BBoolean(true))))._or(CruiseSpeedChangeInProgress.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODnone))).implies(VehicleTryKeepSpeed.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODclose)))._and(ObstacleRelativeSpeed.unequal((RSset(RSset::RSpos))))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(VehicleTryKeepTimeGap.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODveryclose)))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(VehicleTryKeepTimeGap.equal((BBoolean(true)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))._and(ObstacleDistance.unequal((ODset(ODset::ODveryclose))))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(VehicleTryKeepSpeed.equal((BBoolean(true)))))).booleanValue()) {
                CCInitialisationInProgress = static_cast<BBoolean >((BBoolean(true)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void CruiseSpeedChangeFinished() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CruiseSpeedChangeInProgress.equal((BBoolean(true)))).booleanValue()) {
                for(int _0_i = 0; _0_i < ((BOOL).size()); _0_i++) {
                    BBoolean vtks = static_cast<BBoolean >(*(std::next((BOOL).begin(), _0_i)));
                    for(int _1_i = 0; _1_i < ((BOOL).size()); _1_i++) {
                        BBoolean vtktg = static_cast<BBoolean >(*(std::next((BOOL).begin(), _1_i)));
                        if((BOOL).elementOf(vtks)._and((BOOL).elementOf(vtktg))._and(vtks.equal((BBoolean(true)))._or(vtktg.equal((BBoolean(true))))._or(ObstacleStatusJustChanged.equal((BBoolean(true))))._or(CCInitialisationInProgress.equal((BBoolean(true)))))._and(ObstaclePresent.equal((BBoolean(false))).implies(vtktg.equal((BBoolean(false)))))._and(ObstacleDistance.equal((ODset(ODset::ODnone))).implies(vtks.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODclose)))._and(ObstacleRelativeSpeed.unequal((RSset(RSset::RSpos))))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CCInitialisationInProgress.equal((BBoolean(false)))).implies(vtktg.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODveryclose)))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CCInitialisationInProgress.equal((BBoolean(false)))).implies(vtktg.equal((BBoolean(true)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))._and(ObstacleDistance.unequal((ODset(ODset::ODveryclose))))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CCInitialisationInProgress.equal((BBoolean(false)))).implies(vtks.equal((BBoolean(true)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSequal)))._and(ObstacleDistance.equal((ODset(ODset::ODnone)))).implies(vtktg.equal((BBoolean(false)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSneg)))._and(ObstacleDistance.equal((ODset(ODset::ODnone)))).implies(vtktg.equal((BBoolean(false)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))._and(ObstacleDistance.unequal((ODset(ODset::ODveryclose)))).implies(vtktg.equal((BBoolean(false))))).booleanValue()) {
                            BBoolean _ld_VehicleTryKeepTimeGap = static_cast<BBoolean >(VehicleTryKeepTimeGap);
                            BBoolean _ld_VehicleTryKeepSpeed = static_cast<BBoolean >(VehicleTryKeepSpeed);
                            VehicleTryKeepTimeGap = static_cast<BBoolean >(vtktg);
                            VehicleTryKeepSpeed = static_cast<BBoolean >(vtks);
                            break;
                        }
                    }
                }
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void CruiseSpeedChangeDelayFinished() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CruiseSpeedChangeInProgress.equal((BBoolean(true)))._and(VehicleTryKeepSpeed.equal((BBoolean(true)))._or(VehicleTryKeepTimeGap.equal((BBoolean(true))))._or(ObstacleStatusJustChanged.equal((BBoolean(true))))._or(CCInitialisationInProgress.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODnone))).implies(VehicleTryKeepSpeed.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODclose)))._and(ObstacleRelativeSpeed.unequal((RSset(RSset::RSpos))))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CCInitialisationInProgress.equal((BBoolean(false)))).implies(VehicleTryKeepTimeGap.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODveryclose)))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CCInitialisationInProgress.equal((BBoolean(false)))).implies(VehicleTryKeepTimeGap.equal((BBoolean(true)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))._and(ObstacleDistance.unequal((ODset(ODset::ODveryclose))))._and(ObstacleStatusJustChanged.equal((BBoolean(false))))._and(CCInitialisationInProgress.equal((BBoolean(false)))).implies(VehicleTryKeepSpeed.equal((BBoolean(true)))))).booleanValue()) {
                CruiseSpeedChangeInProgress = static_cast<BBoolean >((BBoolean(true)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void CruiseOff() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CruiseActive.equal((BBoolean(true)))).booleanValue()) {
                BBoolean _ld_CruiseActive = static_cast<BBoolean >(CruiseActive);
                BBoolean _ld_VehicleCanKeepSpeed = static_cast<BBoolean >(VehicleCanKeepSpeed);
                BBoolean _ld_VehicleTryKeepSpeed = static_cast<BBoolean >(VehicleTryKeepSpeed);
                BBoolean _ld_VehicleAtCruiseSpeed = static_cast<BBoolean >(VehicleAtCruiseSpeed);
                BBoolean _ld_VehicleTryKeepTimeGap = static_cast<BBoolean >(VehicleTryKeepTimeGap);
                BBoolean _ld_CruiseSpeedAtMax = static_cast<BBoolean >(CruiseSpeedAtMax);
                ODset _ld_ObstacleDistance = static_cast<ODset >(ObstacleDistance);
                BInteger _ld_NumberOfSetCruise = static_cast<BInteger >(NumberOfSetCruise);
                BBoolean _ld_ObstacleStatusJustChanged = static_cast<BBoolean >(ObstacleStatusJustChanged);
                BBoolean _ld_CCInitialisationInProgress = static_cast<BBoolean >(CCInitialisationInProgress);
                BBoolean _ld_CruiseSpeedChangeInProgress = static_cast<BBoolean >(CruiseSpeedChangeInProgress);
                CruiseActive = static_cast<BBoolean >((BBoolean(false)));
                VehicleCanKeepSpeed = static_cast<BBoolean >((BBoolean(false)));
                VehicleTryKeepSpeed = static_cast<BBoolean >((BBoolean(false)));
                VehicleAtCruiseSpeed = static_cast<BBoolean >((BBoolean(false)));
                VehicleTryKeepTimeGap = static_cast<BBoolean >((BBoolean(false)));
                CruiseSpeedAtMax = static_cast<BBoolean >((BBoolean(false)));
                ObstacleDistance = static_cast<ODset >((ODset(ODset::ODnone)));
                NumberOfSetCruise = static_cast<BInteger >((BInteger(0)));
                ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(false)));
                CCInitialisationInProgress = static_cast<BBoolean >((BBoolean(false)));
                CruiseSpeedChangeInProgress = static_cast<BBoolean >((BBoolean(false)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ExternalForcesBecomesExtreme() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((VehicleCanKeepSpeed.equal((BBoolean(true)))).booleanValue()) {
                VehicleCanKeepSpeed = static_cast<BBoolean >((BBoolean(false)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ExternalForcesBecomesNormal() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CruiseActive.equal((BBoolean(true)))._and(VehicleCanKeepSpeed.equal((BBoolean(false))))).booleanValue()) {
                VehicleCanKeepSpeed = static_cast<BBoolean >((BBoolean(true)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void VehicleLeavesCruiseSpeed() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((VehicleAtCruiseSpeed.equal((BBoolean(true)))._and(VehicleCanKeepSpeed.equal((BBoolean(false)))._and(VehicleTryKeepSpeed.equal((BBoolean(true)))))._or(VehicleTryKeepSpeed.equal((BBoolean(false))))).booleanValue()) {
                VehicleAtCruiseSpeed = static_cast<BBoolean >((BBoolean(false)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void VehicleReachesCruiseSpeed() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((CruiseActive.equal((BBoolean(true)))._and(VehicleAtCruiseSpeed.equal((BBoolean(false))))._and(SpeedAboveMax.equal((BBoolean(false))))).booleanValue()) {
                VehicleAtCruiseSpeed = static_cast<BBoolean >((BBoolean(true)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void VehicleExceedsMaxCruiseSpeed() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((SpeedAboveMax.equal((BBoolean(false)))._and(CruiseActive.equal((BBoolean(false)))._or(VehicleCanKeepSpeed.equal((BBoolean(false))))._or(ObstacleStatusJustChanged.equal((BBoolean(false)))._and(CCInitialisationInProgress.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false))))._not()))).booleanValue()) {
                BBoolean _ld_SpeedAboveMax = static_cast<BBoolean >(SpeedAboveMax);
                BBoolean _ld_VehicleAtCruiseSpeed = static_cast<BBoolean >(VehicleAtCruiseSpeed);
                SpeedAboveMax = static_cast<BBoolean >((BBoolean(true)));
                VehicleAtCruiseSpeed = static_cast<BBoolean >((BBoolean(false)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void VehicleFallsBelowMaxCruiseSpeed() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((SpeedAboveMax.equal((BBoolean(true)))).booleanValue()) {
                BBoolean _ld_SpeedAboveMax = static_cast<BBoolean >(SpeedAboveMax);
                if((CruiseActive.equal((BBoolean(true)))._and(CruiseSpeedAtMax.equal((BBoolean(true))))).booleanValue()) {
                    VehicleAtCruiseSpeed = static_cast<BBoolean >((BBoolean(true)));
                } 
                SpeedAboveMax = static_cast<BBoolean >((BBoolean(false)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleDistanceBecomesVeryClose() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstacleDistance.equal((ODset(ODset::ODclose)))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSneg))))).booleanValue()) {
                ODset _ld_ObstacleDistance = static_cast<ODset >(ObstacleDistance);
                BBoolean _ld_ObstacleStatusJustChanged = static_cast<BBoolean >(ObstacleStatusJustChanged);
                ObstacleDistance = static_cast<ODset >((ODset(ODset::ODveryclose)));
                ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleDistanceBecomesClose() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstaclePresent.equal((BBoolean(true)))._and(CruiseActive.equal((BBoolean(true))))._and(ObstacleDistance.equal((ODset(ODset::ODveryclose)))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos))))._or(ObstacleDistance.equal((ODset(ODset::ODnone)))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSneg))))))).booleanValue()) {
                ODset _ld_ObstacleDistance = static_cast<ODset >(ObstacleDistance);
                BBoolean _ld_ObstacleStatusJustChanged = static_cast<BBoolean >(ObstacleStatusJustChanged);
                if((ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))).booleanValue()) {
                    VehicleTryKeepTimeGap = static_cast<BBoolean >((BBoolean(false)));
                } 
                ObstacleDistance = static_cast<ODset >((ODset(ODset::ODclose)));
                ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleDistanceBecomesBig() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstacleDistance.equal((ODset(ODset::ODclose)))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos))))).booleanValue()) {
                BBoolean _ld_ObstacleStatusJustChanged = static_cast<BBoolean >(ObstacleStatusJustChanged);
                ODset _ld_ObstacleDistance = static_cast<ODset >(ObstacleDistance);
                BBoolean _ld_VehicleTryKeepTimeGap = static_cast<BBoolean >(VehicleTryKeepTimeGap);
                ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
                ObstacleDistance = static_cast<ODset >((ODset(ODset::ODnone)));
                VehicleTryKeepTimeGap = static_cast<BBoolean >((BBoolean(false)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleStartsTravelFaster() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstaclePresent.equal((BBoolean(true)))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSequal))))).booleanValue()) {
                RSset _ld_ObstacleRelativeSpeed = static_cast<RSset >(ObstacleRelativeSpeed);
                if((CruiseActive.equal((BBoolean(true)))).booleanValue()) {
                    ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
                } 
                if((ObstacleDistance.unequal((ODset(ODset::ODveryclose)))).booleanValue()) {
                    VehicleTryKeepTimeGap = static_cast<BBoolean >((BBoolean(false)));
                } 
                ObstacleRelativeSpeed = static_cast<RSset >((RSset(RSset::RSpos)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleStopsTravelFaster() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))).booleanValue()) {
                RSset _ld_ObstacleRelativeSpeed = static_cast<RSset >(ObstacleRelativeSpeed);
                if((CruiseActive.equal((BBoolean(true)))).booleanValue()) {
                    ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
                } 
                ObstacleRelativeSpeed = static_cast<RSset >((RSset(RSset::RSequal)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleStartsTravelSlower() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstacleRelativeSpeed.equal((RSset(RSset::RSequal)))).booleanValue()) {
                RSset _ld_ObstacleRelativeSpeed = static_cast<RSset >(ObstacleRelativeSpeed);
                if((CruiseActive.equal((BBoolean(true)))).booleanValue()) {
                    ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
                } 
                ObstacleRelativeSpeed = static_cast<RSset >((RSset(RSset::RSneg)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleStopsTravelSlower() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstacleRelativeSpeed.equal((RSset(RSset::RSneg)))).booleanValue()) {
                RSset _ld_ObstacleRelativeSpeed = static_cast<RSset >(ObstacleRelativeSpeed);
                if((CruiseActive.equal((BBoolean(true)))).booleanValue()) {
                    ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
                } 
                ObstacleRelativeSpeed = static_cast<RSset >((RSset(RSset::RSequal)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleAppearsWhenCruiseActive() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstaclePresent.equal((BBoolean(false)))._and(CruiseActive.equal((BBoolean(true))))).booleanValue()) {
                BBoolean _ld_ObstaclePresent = static_cast<BBoolean >(ObstaclePresent);
                BBoolean _ld_ObstacleStatusJustChanged = static_cast<BBoolean >(ObstacleStatusJustChanged);
                ObstacleRelativeSpeed = static_cast<RSset >(_RSset.complement((BSet<RSset >((std::vector<RSset>){(RSset(RSset::RSnone))}))).nondeterminism());
                ObstacleDistance = static_cast<ODset >(_ODset.complement((BSet<ODset >((std::vector<ODset>){(ODset(ODset::ODnone))}))).nondeterminism());
                ObstaclePresent = static_cast<BBoolean >((BBoolean(true)));
                ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleAppearsWhenCruiseInactive() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstaclePresent.equal((BBoolean(false)))._and(CruiseActive.equal((BBoolean(false))))).booleanValue()) {
                BBoolean _ld_ObstaclePresent = static_cast<BBoolean >(ObstaclePresent);
                ODset _ld_ObstacleDistance = static_cast<ODset >(ObstacleDistance);
                ObstacleRelativeSpeed = static_cast<RSset >(_RSset.complement((BSet<RSset >((std::vector<RSset>){(RSset(RSset::RSnone))}))).nondeterminism());
                ObstaclePresent = static_cast<BBoolean >((BBoolean(true)));
                ObstacleDistance = static_cast<ODset >((ODset(ODset::ODnone)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleDisappears() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstaclePresent.equal((BBoolean(true)))).booleanValue()) {
                BBoolean _ld_ObstaclePresent = static_cast<BBoolean >(ObstaclePresent);
                RSset _ld_ObstacleRelativeSpeed = static_cast<RSset >(ObstacleRelativeSpeed);
                ODset _ld_ObstacleDistance = static_cast<ODset >(ObstacleDistance);
                BBoolean _ld_VehicleTryKeepTimeGap = static_cast<BBoolean >(VehicleTryKeepTimeGap);
                if((CruiseActive.equal((BBoolean(true)))).booleanValue()) {
                    ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(true)));
                } 
                ObstaclePresent = static_cast<BBoolean >((BBoolean(false)));
                ObstacleRelativeSpeed = static_cast<RSset >((RSset(RSset::RSnone)));
                ObstacleDistance = static_cast<ODset >((ODset(ODset::ODnone)));
                VehicleTryKeepTimeGap = static_cast<BBoolean >((BBoolean(false)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void VehicleManageObstacle() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstacleStatusJustChanged.equal((BBoolean(true)))).booleanValue()) {
                for(int _0_i = 0; _0_i < ((BOOL).size()); _0_i++) {
                    BBoolean vtks = static_cast<BBoolean >(*(std::next((BOOL).begin(), _0_i)));
                    for(int _1_i = 0; _1_i < ((BOOL).size()); _1_i++) {
                        BBoolean vtktg = static_cast<BBoolean >(*(std::next((BOOL).begin(), _1_i)));
                        if((BOOL).elementOf(vtks)._and((BOOL).elementOf(vtktg))._and(vtks.equal((BBoolean(true)))._or(vtktg.equal((BBoolean(true))))._or(CCInitialisationInProgress.equal((BBoolean(true))))._or(CruiseSpeedChangeInProgress.equal((BBoolean(true)))))._and(ObstaclePresent.equal((BBoolean(false))).implies(vtktg.equal((BBoolean(false)))))._and(ObstacleDistance.equal((ODset(ODset::ODnone))).implies(vtks.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODclose)))._and(ObstacleRelativeSpeed.unequal((RSset(RSset::RSpos))))._and(CCInitialisationInProgress.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(vtktg.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODveryclose)))._and(CCInitialisationInProgress.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(vtktg.equal((BBoolean(true)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))._and(ObstacleDistance.unequal((ODset(ODset::ODveryclose))))._and(CCInitialisationInProgress.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(vtks.equal((BBoolean(true)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSequal)))._and(ObstacleDistance.equal((ODset(ODset::ODnone)))).implies(vtktg.equal((BBoolean(false)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSneg)))._and(ObstacleDistance.equal((ODset(ODset::ODnone)))).implies(vtktg.equal((BBoolean(false)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))._and(ObstacleDistance.unequal((ODset(ODset::ODveryclose)))).implies(vtktg.equal((BBoolean(false))))).booleanValue()) {
                            BBoolean _ld_VehicleTryKeepTimeGap = static_cast<BBoolean >(VehicleTryKeepTimeGap);
                            BBoolean _ld_VehicleTryKeepSpeed = static_cast<BBoolean >(VehicleTryKeepSpeed);
                            VehicleTryKeepTimeGap = static_cast<BBoolean >(vtktg);
                            VehicleTryKeepSpeed = static_cast<BBoolean >(vtks);
                            break;
                        }
                    }
                }
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void ObstacleBecomesOld() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((ObstacleStatusJustChanged.equal((BBoolean(true)))._and(VehicleTryKeepSpeed.equal((BBoolean(true)))._or(VehicleTryKeepTimeGap.equal((BBoolean(true))))._or(CCInitialisationInProgress.equal((BBoolean(true))))._or(CruiseSpeedChangeInProgress.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODnone))).implies(VehicleTryKeepSpeed.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODclose)))._and(ObstacleRelativeSpeed.unequal((RSset(RSset::RSpos))))._and(CCInitialisationInProgress.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(VehicleTryKeepTimeGap.equal((BBoolean(true)))))._and(ObstacleDistance.equal((ODset(ODset::ODveryclose)))._and(CCInitialisationInProgress.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(VehicleTryKeepTimeGap.equal((BBoolean(true)))))._and(ObstacleRelativeSpeed.equal((RSset(RSset::RSpos)))._and(ObstacleDistance.unequal((ODset(ODset::ODveryclose))))._and(CCInitialisationInProgress.equal((BBoolean(false))))._and(CruiseSpeedChangeInProgress.equal((BBoolean(false)))).implies(VehicleTryKeepSpeed.equal((BBoolean(true)))))).booleanValue()) {
                ObstacleStatusJustChanged = static_cast<BBoolean >((BBoolean(false)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

};
#endif

